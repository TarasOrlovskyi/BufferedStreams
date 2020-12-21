package com.orlovskyi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
//import java.io.BufferedOutputStream;
//import java.io.BufferedInputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BufferedOutputStreamTest {

    ByteArrayInputStream byteArrayInputStream;
    byte[] arrayForInput;
    String contentString;
    FileOutputStream fileOutputStream;
    FileInputStream fileInputStream;
    StringBuilder stringBuilder;

    @BeforeEach
    void before() {
        try {
            File file = new File("testOut.txt");
            fileOutputStream = new FileOutputStream(file);
            fileInputStream = new FileInputStream(file);
            file.deleteOnExit();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        stringBuilder = new StringBuilder();
        contentString = "I'm testing a BufferedOutputStream! And i want to solve this task as fast as possible!";
        arrayForInput = contentString.getBytes();
        byteArrayInputStream = new ByteArrayInputStream(arrayForInput);
    }

    @Test
    void testWriteFromArrayWithoutOffANDLen() {
        try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream)) {
            bufferedOutputStream.write(arrayForInput);
        } catch (IOException e) {
            e.getStackTrace();
        }
        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream)) {
            int count;
            byte[] buf = new byte[8];
            while ((count = bufferedInputStream.read(buf)) != -1) {
                stringBuilder.append(new String(buf, 0, count));
            }
            assertEquals("I'm testing a BufferedOutputStream! And i want to solve this task as fast as possible!", stringBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testWriteFromArrayWithOffZeroANDLenLessArrayLength() {
        try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream)) {
            bufferedOutputStream.write(arrayForInput, 0, arrayForInput.length - 10);
        } catch (IOException e) {
            e.getStackTrace();
        }
        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream)) {
            int a;
            while ((a = bufferedInputStream.read()) != -1) {
                stringBuilder.append((char) a);
            }
            assertEquals("I'm testing a BufferedOutputStream! And i want to solve this task as fast as", stringBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testWriteFromArrayWithOffZeroANDLenEqualArrayLength() {
        try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream)) {
            bufferedOutputStream.write(arrayForInput, 0, arrayForInput.length);
        } catch (IOException e) {
            e.getStackTrace();
        }
        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream)) {
            int a;
            while ((a = bufferedInputStream.read()) != -1) {
                stringBuilder.append((char) a);
            }
            assertEquals("I'm testing a BufferedOutputStream! And i want to solve this task as fast as possible!", stringBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testWriteFromArrayWithOffZeroANDLenMoreArrayLength() {
        try (OutputStream outputStream = new BufferedOutputStream(fileOutputStream)) {
            assertThrows(ArrayIndexOutOfBoundsException.class, () -> {outputStream.write(arrayForInput, 0, arrayForInput.length + 10);outputStream.close();});
        } catch (IOException e) {
            e.getStackTrace();
        }
    }

    @Test
    void testWriteFromArrayWithOffZeroANDLenLessZero() {
        try (OutputStream outputStream = new BufferedOutputStream(fileOutputStream)) {
            assertThrows(ArrayIndexOutOfBoundsException.class, () -> {outputStream.write(arrayForInput, 0, 0 - arrayForInput.length);outputStream.close();});
        } catch (IOException e) {
            e.getStackTrace();
        }
    }

    @Test
    void testWriteFromArrayWithOffNotZeroANDLenLessArrayLength() {
        try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream)) {
            bufferedOutputStream.write(arrayForInput, 4, arrayForInput.length - 5);
        } catch (IOException e) {
            e.getStackTrace();
        }
        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream)) {
            int a;
            while ((a = bufferedInputStream.read()) != -1) {
                stringBuilder.append((char) a);
            }
            assertEquals("testing a BufferedOutputStream! And i want to solve this task as fast as possible", stringBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testWriteByByte() {
        try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream)) {
            int a;
            while ((a = byteArrayInputStream.read()) != -1) {
                bufferedOutputStream.write(a);
            }
        } catch (IOException e) {
            e.getStackTrace();
        }
        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream)) {
            int a;
            while ((a = bufferedInputStream.read()) != -1) {
                stringBuilder.append((char) a);
            }
            assertEquals("I'm testing a BufferedOutputStream! And i want to solve this task as fast as possible!", stringBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}