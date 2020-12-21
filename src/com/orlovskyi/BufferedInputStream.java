package com.orlovskyi;

import java.io.IOException;
import java.io.InputStream;

public class BufferedInputStream extends InputStream {
    private InputStream inputStream;
    private static final int CAPACITY = 8 * 1024;
    private byte[] arrayForInputByByte;
    private int index;
    private int countOfElementsRead;

    public BufferedInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
        arrayForInputByByte = new byte[CAPACITY];
    }

    public BufferedInputStream(InputStream inputStream, int capacity) {
        this.inputStream = inputStream;
        if (capacity <= 0) {
            throw new IllegalArgumentException("Buffer Size <= 0!");
        } else arrayForInputByByte = new byte[capacity];
    }

    @Override
    public int read() throws IOException {
        if (countOfElementsRead == index) {
            countOfElementsRead = inputStream.read(arrayForInputByByte);
            index = 0;
        }

        if (countOfElementsRead == -1) {
            return -1;
        }
        return arrayForInputByByte[index++];
    }

    @Override
    public int read(byte[] b) throws IOException {
        return read(b, 0, b.length);
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        arrayForInputByByte = new byte[b.length];
        int a;
        int count = 0;
        int lenCount;
        int indexB = off;
        if (index == countOfElementsRead) {
            while ((a = read()) != -1) {
                if (len > (b.length - off) || off < 0 || len < 0) {
                    throw new IndexOutOfBoundsException("");
                } else {
                    lenCount = len - count;
                    if (lenCount != 0 && len <= b.length) {
                        b[indexB++] = (byte) a;
                        count++;
                    }
                }
                if (count == countOfElementsRead || count == len) {
                    return count;
                }
            }
        }
        if (countOfElementsRead == -1) {
            return -1;
        }
        return count;
    }

    @Override
    public void close() throws IOException {
        inputStream.close();
    }

}