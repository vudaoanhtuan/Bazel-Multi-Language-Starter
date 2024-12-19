package com.example.proto;

import java.io.BufferedOutputStream;
import java.io.IOException;
import org.apache.parquet.io.OutputFile;
import org.apache.parquet.io.PositionOutputStream;

public class BufferedOutputFile implements OutputFile {

    private final BufferedOutputStream out;

    public BufferedOutputFile(BufferedOutputStream out) {
        this.out = out;
    }

    @Override
    public PositionOutputStream create(long blockSizeHint) {
        return new BufferedPositionOutputStream(this.out);
    }

    @Override
    public PositionOutputStream createOrOverwrite(long blockSizeHint) {
        return new BufferedPositionOutputStream(this.out);
    }

    @Override
    public boolean supportsBlockSize() {
        return false;
    }

    @Override
    public long defaultBlockSize() {
        return 0;
    }
}

class BufferedPositionOutputStream extends PositionOutputStream {

    private final BufferedOutputStream out;

    public BufferedPositionOutputStream(BufferedOutputStream out) {
        this.out = out;
    }

    int pos = 0;

    @Override
    public long getPos() {
        return pos;
    }

    @Override
    public void flush() throws IOException {
        out.flush();
    }

    @Override
    public void close() throws IOException {
        out.close();
    }

    @Override
    public void write(int b) throws IOException {
        out.write(b);
        pos++;
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        out.write(b, off, len);
        pos += len;
    }
}
