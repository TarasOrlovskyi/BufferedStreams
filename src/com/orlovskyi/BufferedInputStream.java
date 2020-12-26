package com.orlovskyi;

import java.io.IOException;
import java.io.InputStream;

public class BufferedInputStream extends InputStream {
    private InputStream inputStream;
    private static final int CAPACITY = 8 * 1024;
    private byte[] buffer;
    private int index;
    private int countOfElementsRead;

    public BufferedInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
        buffer = new byte[CAPACITY];
    }

    public BufferedInputStream(InputStream inputStream, int capacity) {
        this.inputStream = inputStream;
        if (capacity <= 0) {
            throw new IllegalArgumentException("Buffer Size <= 0!");
        } else buffer = new byte[capacity];
    }

    @Override
    public int read() throws IOException {
        if (countOfElementsRead == index) {
            countOfElementsRead = inputStream.read(buffer);
            index = 0;
        }
        if (countOfElementsRead == -1) {
            return -1;
        }
        return buffer[index++];
    }

    @Override
    public int read(byte[] b) throws IOException {
        return read(b, 0, b.length);
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        if (b == null) {
            throw new NullPointerException();
        }
        if (off < 0 || len < 0 || len + off > b.length) {
            throw new IndexOutOfBoundsException();
        }

        int count = 0;
        int availableDataBuffer = countOfElementsRead - index;

        if (countOfElementsRead == -1){
            return -1;
        }

        if (availableDataBuffer > 0) {
            if (len <= availableDataBuffer) {
                System.arraycopy(buffer, index, b, off, len);
                count = len;
                index += len;
            } else {
                System.arraycopy(buffer, index, b, off, availableDataBuffer);
                count = availableDataBuffer;
                index = 0;
                len -= availableDataBuffer;
                off += availableDataBuffer;
                if (len >= buffer.length) {
                    count += inputStream.read(b, off, len);
                    return count;
                } else {
                    countOfElementsRead = inputStream.read(buffer);
                    if (countOfElementsRead != -1) {
                        System.arraycopy(buffer, index, b, off, len);
                        index += len;
                        count += len;
                    } else return count;
                }
            }
        } else {
            if (len >= buffer.length) {
                count = inputStream.read(b, off, len);
            } else {
                countOfElementsRead = inputStream.read(buffer);
                if (countOfElementsRead <= len) {
                    System.arraycopy(buffer, index, b, off, countOfElementsRead);
                    index += countOfElementsRead;
                    count = countOfElementsRead;
                } else {
                    System.arraycopy(buffer, index, b, off, len);
                    index += len;
                    count = len;
                }
            }
        }
        return count;
    }

    @Override
    public void close() throws IOException {
        inputStream.close();
    }

}