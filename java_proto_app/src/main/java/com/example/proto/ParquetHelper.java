package com.example.proto;

import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.Message;
import com.google.protobuf.MessageOrBuilder;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.parquet.hadoop.ParquetFileWriter;
import org.apache.parquet.hadoop.ParquetReader;
import org.apache.parquet.hadoop.ParquetWriter;
import org.apache.parquet.hadoop.metadata.CompressionCodecName;
import org.apache.parquet.proto.ProtoParquetReader;
import org.apache.parquet.proto.ProtoParquetWriter;

public class ParquetHelper {

    private ParquetHelper() {}

    public static <T extends Message> void writeParquet(Iterator<T> data, Class<T> clazz, OutputStream out) throws IOException {
        ParquetFileWriter.Mode writeMode = ParquetFileWriter.Mode.OVERWRITE;
        CompressionCodecName compressionCodecName = CompressionCodecName.SNAPPY;
        ProtoParquetWriter.Builder<T> writerBuilder = ProtoParquetWriter
                .<T>builder(new BufferedOutputFile(new BufferedOutputStream(out)))
                .withMessage(clazz)
                .withWriteMode(writeMode)
                .withCompressionCodec(compressionCodecName);

        try (ParquetWriter<T> writer = writerBuilder.build()) {
            while (data.hasNext()) {
                writer.write(data.next());
            }
        }
    }

    public static <T extends Message, B extends GeneratedMessage.Builder<B> & MessageOrBuilder> List<T> readParquet(byte[] data) throws IOException {
        var readerBuilder = ProtoParquetReader.<B>builder(new BufferedInputFile(data));
        List<T> result = new ArrayList<>();
        try (ParquetReader<B> reader = readerBuilder.build()) {
            B rec = reader.read();
            while (rec != null) {
                //noinspection unchecked
                result.add((T) rec.build());
                rec = reader.read();
            }
        }
        return result;
    }
}
