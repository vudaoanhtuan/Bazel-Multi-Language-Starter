package com.example.proto;

import java.io.ByteArrayInputStream;
import org.apache.parquet.io.DelegatingSeekableInputStream;
import org.apache.parquet.io.InputFile;
import org.apache.parquet.io.SeekableInputStream;

public class BufferedInputFile implements InputFile {
    private final byte[] data;

    public BufferedInputFile(byte[] data) {
        this.data = data;
    }

    @Override
    public long getLength() {
        return this.data.length;
    }

    @Override
    public SeekableInputStream newStream() {
        return new DelegatingSeekableByteArrayInputStream(new SeekableByteArrayInputStream(this.data));
    }
}

class SeekableByteArrayInputStream extends ByteArrayInputStream {
    public SeekableByteArrayInputStream(byte[] buf) {
        super(buf);
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public int getPos() {
        return this.pos;
    }
}

class DelegatingSeekableByteArrayInputStream extends DelegatingSeekableInputStream {
    public DelegatingSeekableByteArrayInputStream(SeekableByteArrayInputStream seekableByteArrayInputStream) {
        super(seekableByteArrayInputStream);
    }

    @Override
    public void seek(long newPos) {
        ((SeekableByteArrayInputStream) this.getStream()).setPos((int) newPos);
    }

    @Override
    public long getPos() {
        return ((SeekableByteArrayInputStream) this.getStream()).getPos();
    }
}
