package com.orlovskyi;

import java.io.IOException;
import java.io.OutputStream;

public class BufferedOutputStream extends OutputStream {
    private OutputStream outputStream;
    private static final int CAPACITY = 8 * 1024;
    private byte[] arrayForOutput;
    int index;

    public BufferedOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
        arrayForOutput = new byte[CAPACITY];
    }

    public BufferedOutputStream(OutputStream outputStream, int capacity) {
        this.outputStream = outputStream;
        arrayForOutput = new byte[capacity];
    }

    @Override
    public void write(int i) throws IOException {
        arrayForOutput[index++] = (byte) i;

        if (index == arrayForOutput.length) {
            outputStream.write(arrayForOutput);
            index = 0;
        }
    }

    @Override
    public void write(byte[] b) throws IOException {
        write(b, 0, b.length);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        if (len < 0 || off < 0 || len > b.length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        int countFreeData = arrayForOutput.length - index;
        if (countFreeData >= len) {
            System.arraycopy(b, off, arrayForOutput, index, len);
            index += len;
        } else {
            if (index!=0){
                flush();
            }
            outputStream.write(b, off, len);
        }
    }

    @Override
    public void close() throws IOException {
        if (index != arrayForOutput.length && index > 0) {
            flush();
        }
        outputStream.close();
    }

    @Override
    public void flush() throws IOException {
        outputStream.write(arrayForOutput, 0, index);
        index = 0;
    }
}