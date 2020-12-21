package com.orlovskyi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
//import java.io.BufferedInputStream;
//import java.io.BufferedOutputStream;

import static org.junit.jupiter.api.Assertions.*;

class BufferedInputStreamTest {

    private StringBuilder stringBuilder;
    byte[] byteArrayWithContent;
    ByteArrayInputStream byteArrayInputStream;
    int readResult;
    byte[] bufferedResult;

    @BeforeEach
    void before() {
        stringBuilder = new StringBuilder();
        String content = "Hello java! I am testing BufferedStream! :)";
        byteArrayWithContent = content.getBytes();
        byteArrayInputStream = new ByteArrayInputStream(byteArrayWithContent);
    }

    //**** test method read() #1 ****
    // sizeBufferedIS < file.length,
    // byte[] don't use
    @Test
    void testReadWithoutParameterWhenBufferedSizeLessSizeOfStream() {
        try (InputStream inputStream = new BufferedInputStream(
                byteArrayInputStream,
                byteArrayWithContent.length / 2)) {
            while ((readResult = inputStream.read()) != -1) {
                stringBuilder.append((char) readResult);
            }
            assertEquals("Hello java! I am testing BufferedStream! :)", stringBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    //**** test method read() #2 ****
    // sizeBufferedIS > file.length,
    // byte[] don't use
    @Test
    void testReadWithoutParameterWhenBufferedSizeMoreSizeOfStream() {
        try (InputStream inputStream = new BufferedInputStream(
                byteArrayInputStream,
                byteArrayWithContent.length * 2)) {
            while ((readResult = inputStream.read()) != -1) {
                stringBuilder.append((char) readResult);
            }
            assertEquals("Hello java! I am testing BufferedStream! :)", stringBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    //**** test method read(byte[] b) #1 ****
    // sizeBufferedIS > file.length
    // byte[].length < file.length
    @Test
    void testReadWithArrayAsParameterWhenBufferedSizeMoreSizeOfStreamANDArraySizeLessSizeOfByteArrayIS() {
        try (InputStream inputStream = new BufferedInputStream(
                byteArrayInputStream,
                byteArrayWithContent.length * 2)) {
            bufferedResult = new byte[byteArrayWithContent.length - 10];
            readResult = inputStream.read(bufferedResult);
            for (int i = 0; i < readResult; i++) {
                stringBuilder.append((char) bufferedResult[i]);
            }
            assertEquals("Hello java! I am testing Buffered", stringBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    //**** test method read(byte[] b) #2 ****
    // sizeBufferedIS > file.length
    // byte[].length = file.length
    @Test
    void testReadWithArrayAsParameterWhenBufferedSizeMoreSizeOfStreamANDArraySizeEqualSizeOfByteArrayIS() {
        try (InputStream inputStream = new BufferedInputStream(
                byteArrayInputStream,
                byteArrayWithContent.length * 2)) {
            bufferedResult = new byte[byteArrayWithContent.length];
            readResult = inputStream.read(bufferedResult);
            for (int i = 0; i < readResult; i++) {
                stringBuilder.append((char) bufferedResult[i]);
            }
            assertEquals("Hello java! I am testing BufferedStream! :)", stringBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    //**** test method read(byte[] b) #3 ****
    // sizeBufferedIS > file.length
    // byte[].length > file.length
    @Test
    void testReadWithArrayAsParameterWhenBufferedSizeMoreSizeOfStreamANDArraySizeMoreSizeOfByteArrayIS() {
        try (InputStream inputStream = new BufferedInputStream(
                byteArrayInputStream,
                byteArrayWithContent.length * 2)) {
            bufferedResult = new byte[byteArrayWithContent.length + 10];
            readResult = inputStream.read(bufferedResult);
            for (int i = 0; i < readResult; i++) {
                stringBuilder.append((char) bufferedResult[i]);
            }
            assertEquals("Hello java! I am testing BufferedStream! :)", stringBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    //**** test method read(byte[] b) #4 ****
    // sizeBufferedIS < file.length
    // byte[].length < file.length
    @Test
    void testReadWithArrayAsParameterWhenBufferedSizeLessSizeOfStreamANDArraySizeLessSizeOfByteArrayIS() {
        try (InputStream inputStream = new BufferedInputStream(
                byteArrayInputStream,
                byteArrayWithContent.length / 2)) {
            bufferedResult = new byte[byteArrayWithContent.length - 10];
            readResult = inputStream.read(bufferedResult);
            for (int i = 0; i < readResult; i++) {
                stringBuilder.append((char) bufferedResult[i]);
            }
            assertEquals("Hello java! I am testing Buffered", stringBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    //**** test method read(byte[] b) #5 ****
    // sizeBufferedIS < file.length
    // byte[].length = file.length
    @Test
    void testReadWithArrayAsParameterWhenBufferedSizeLessSizeOfStreamANDArraySizeEqualSizeOfByteArrayIS() {
        try (InputStream inputStream = new BufferedInputStream(
                byteArrayInputStream,
                byteArrayWithContent.length / 2)) {
            bufferedResult = new byte[byteArrayWithContent.length];
            readResult = inputStream.read(bufferedResult);
            for (int i = 0; i < readResult; i++) {
                stringBuilder.append((char) bufferedResult[i]);
            }
            assertEquals("Hello java! I am testing BufferedStream! :)", stringBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    //**** test method read(byte[] b) #6 ****
    // sizeBufferedIS < file.length
    // byte[].length > file.length
    @Test
    void testReadWithArrayAsParameterWhenBufferedSizeLessSizeOfStreamANDArraySizeMoreSizeOfByteArrayIS() {
        try (InputStream inputStream = new BufferedInputStream(
                byteArrayInputStream,
                byteArrayWithContent.length / 2)) {
            bufferedResult = new byte[byteArrayWithContent.length + 10];
            readResult = inputStream.read(bufferedResult);
            for (int i = 0; i < readResult; i++) {
                stringBuilder.append((char) bufferedResult[i]);
            }
            assertEquals("Hello java! I am testing BufferedStream! :)", stringBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    //**** test method read(byte[] b, off, len) #1 ****
    // sizeBufferedIS < file.length
    // byte[].length = file.length
    // off = 0
    // len < byte[].length
    @Test
    void testReadWithParametersWhenBufferedSizeLessSizeOfStreamANDArraySizeEqualSizeOfByteArrayISANDOffZeroANDLenLessSizeOfStream() {
        try (InputStream inputStream = new BufferedInputStream(
                byteArrayInputStream,
                byteArrayWithContent.length / 2)) {
            bufferedResult = new byte[byteArrayWithContent.length];
            readResult = inputStream.read(bufferedResult, 0, byteArrayWithContent.length - 10);
            for (int i = 0; i < readResult; i++) {
                stringBuilder.append((char) bufferedResult[i]);
            }
            assertEquals("Hello java! I am testing Buffered", stringBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    //**** test method read(byte[] b, off, len) #2 ****
    // sizeBufferedIS < file.length
    // byte[].length = file.length
    // off = 0
    // len = byte[].length
    @Test
    void testReadWithParametersWhenBufferedSizeLessSizeOfStreamANDArraySizeEqualSizeOfByteArrayISANDOffZeroANDLenEqualSizeOfStream() {
        try (InputStream inputStream = new BufferedInputStream(
                byteArrayInputStream,
                byteArrayWithContent.length / 2)) {
            bufferedResult = new byte[byteArrayWithContent.length];
            readResult = inputStream.read(bufferedResult, 0, byteArrayWithContent.length);
            for (int i = 0; i < readResult; i++) {
                stringBuilder.append((char) bufferedResult[i]);
            }
            assertEquals("Hello java! I am testing BufferedStream! :)", stringBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    //**** test method read(byte[] b, off, len) #3 ****
    // sizeBufferedIS < file.length
    // byte[].length = file.length
    // off = 0
    // len > byte[].length
    @Test
    void testReadWithParametersWhenBufferedSizeLessSizeOfStreamANDArraySizeEqualSizeOfByteArrayISANDOffZeroANDLenMoreSizeOfStream() {
        try (InputStream inputStream = new BufferedInputStream(
                byteArrayInputStream,
                byteArrayWithContent.length / 2)) {
            bufferedResult = new byte[byteArrayWithContent.length];
            assertThrows(IndexOutOfBoundsException.class, () -> {inputStream.read(bufferedResult, 0, byteArrayWithContent.length + 10); inputStream.close();});
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    //**** test method read(byte[] b, off, len) #4 ****
    // sizeBufferedIS < file.length
    // byte[].length = file.length
    // off = 3
    // len < byte[].length
    @Test
    void testReadWithParametersWhenBufferedSizeLessSizeOfStreamANDArraySizeEqualSizeOfByteArrayISANDOffThreeANDLenLessSizeOfStream() {
        try (InputStream inputStream = new BufferedInputStream(
                byteArrayInputStream,
                byteArrayWithContent.length / 2)) {
            bufferedResult = new byte[byteArrayWithContent.length];
            readResult = inputStream.read(bufferedResult, 3, byteArrayWithContent.length - 10);
            for (int i = 0; i < readResult; i++) {
                stringBuilder.append((char) bufferedResult[i]);
            }
            assertEquals("\u0000\u0000\u0000Hello java! I am testing Buffe", stringBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    //**** test method read(byte[] b, off, len) #5 ****
    // sizeBufferedIS < file.length
    // byte[].length = file.length
    // off = 3
    // len = byte[].length
    @Test
    void testReadWithParametersWhenBufferedSizeLessSizeOfStreamANDArraySizeEqualSizeOfByteArrayISANDOffThreeANDLenEqualSizeOfStream() {
        try (InputStream inputStream = new BufferedInputStream(
                byteArrayInputStream,
                byteArrayWithContent.length / 2)) {
            bufferedResult = new byte[byteArrayWithContent.length];
            assertThrows(IndexOutOfBoundsException.class, () -> {inputStream.read(bufferedResult, 3, byteArrayWithContent.length); inputStream.close();});
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    //**** test method read(byte[] b, off, len) #6 ****
    // sizeBufferedIS < file.length
    // byte[].length = file.length
    // off = 3
    // len > byte[].length
    @Test
    void testReadWithParametersWhenBufferedSizeLessSizeOfStreamANDArraySizeEqualSizeOfByteArrayISANDOffThreeANDLenMoreSizeOfStream() {
        try (InputStream inputStream = new BufferedInputStream(
                byteArrayInputStream,
                byteArrayWithContent.length / 2)) {
            bufferedResult = new byte[byteArrayWithContent.length];
            assertThrows(IndexOutOfBoundsException.class, () -> {inputStream.read(bufferedResult, 3, byteArrayWithContent.length + 10); inputStream.close();});
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    //**** test method read(byte[] b, off, len) #7 ****
    // sizeBufferedIS < file.length
    // byte[].length < file.length
    // off = 3
    // len < byte[].length
    // len < available bytes
    @Test
    void testReadWithParametersWhenBufferedSizeLessSizeOfStreamANDArraySizeLessSizeOfByteArrayISANDOffThreeANDLenLessSizeOfStreamANDLenLessAvailableBytes() {
        try (InputStream inputStream = new BufferedInputStream(
                byteArrayInputStream,
                byteArrayWithContent.length / 2)) {
            bufferedResult = new byte[byteArrayWithContent.length - 5];
            readResult = inputStream.read(bufferedResult, 3, byteArrayWithContent.length - 10);
            for (int i = 0; i < readResult; i++) {
                stringBuilder.append((char) bufferedResult[i]);
            }
            assertEquals("\u0000\u0000\u0000Hello java! I am testing Buffe", stringBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    //**** test method read(byte[] b, off, len) #8 ****
    // sizeBufferedIS < file.length
    // byte[].length < file.length
    // off = 3
    // len < byte[].length
    // len > available bytes
    @Test
    void testReadWithParametersWhenBufferedSizeLessSizeOfStreamANDArraySizeLessSizeOfByteArrayISANDOffThreeANDLenLessSizeOfStreamANDLenMoreAvailableBytes() {
        try (InputStream inputStream = new BufferedInputStream(
                byteArrayInputStream,
                byteArrayWithContent.length / 2)) {
            bufferedResult = new byte[byteArrayWithContent.length - 5];
            assertThrows(IndexOutOfBoundsException.class, () -> {inputStream.read(bufferedResult, 3, byteArrayWithContent.length - 6);inputStream.close();});
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    //**** test method read(byte[] b, off, len) #9 ****
    // sizeBufferedIS > file.length
    // byte[].length < file.length
    // off = 3
    // len < byte[].length
    // len < available bytes
    @Test
    void testReadWithParametersWhenBufferedSizeMoreSizeOfStreamANDArraySizeLessSizeOfByteArrayISANDOffThreeANDLenLessSizeOfStreamANDLenLessAvailableBytes() {
        try (InputStream inputStream = new BufferedInputStream(
                byteArrayInputStream,
                byteArrayWithContent.length * 2)) {
            bufferedResult = new byte[byteArrayWithContent.length - 5];
            readResult = inputStream.read(bufferedResult, 3, byteArrayWithContent.length - 10);
            for (int i = 0; i < readResult; i++) {
                stringBuilder.append((char) bufferedResult[i]);
            }
            assertEquals("\u0000\u0000\u0000Hello java! I am testing Buffe", stringBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }
}