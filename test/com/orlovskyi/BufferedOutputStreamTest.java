package com.orlovskyi;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
//import java.io.BufferedOutputStream;
//import java.io.BufferedInputStream;
import static org.junit.jupiter.api.Assertions.*;


class BufferedOutputStreamTest {

    ByteArrayInputStream byteArrayInputStream;
    byte[] arrayForInput;
    String contentString;
    FileOutputStream fileOutputStream;
    FileInputStream fileInputStream;
    StringBuilder stringBuilder;

    ByteArrayOutputStream byteArrayOutputStreamWithoutFile;
    BufferedOutputStream bufferedOutputStreamWithoutFile;

    @BeforeEach
    void before() {
        byteArrayOutputStreamWithoutFile = new ByteArrayOutputStream();
        bufferedOutputStreamWithoutFile = new BufferedOutputStream(byteArrayOutputStreamWithoutFile, 3);

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
    void testWriteFromArrayByByte() throws IOException {

        byte[] byteTestArr = {4, 78, 24, 56, 21, 44, 22, 37};
        for (int i = 0; i < byteTestArr.length; i++) {
            bufferedOutputStreamWithoutFile.write(byteTestArr[i]);
        }
        bufferedOutputStreamWithoutFile.flush();
        byte[] bytesBAOS = byteArrayOutputStreamWithoutFile.toByteArray();
        assertEquals(8, bytesBAOS.length);
        assertEquals(4, bytesBAOS[0]);
        assertEquals(78, bytesBAOS[1]);
        assertEquals(24, bytesBAOS[2]);
        assertEquals(56, bytesBAOS[3]);
        assertEquals(21, bytesBAOS[4]);
        assertEquals(44, bytesBAOS[5]);
        assertEquals(22, bytesBAOS[6]);
        assertEquals(37, bytesBAOS[7]);
    }

    @Test
    void testWriteFromArrayWithoutOffANDLen() throws IOException {

        byte[] byteTestArr = {4, 78, 24, 56, 21, 44, 22, 37};
        bufferedOutputStreamWithoutFile.write(byteTestArr);
        //bufferedOutputStream.flush();
        byte[] bytesBAOS = byteArrayOutputStreamWithoutFile.toByteArray();
        assertEquals(8, bytesBAOS.length);
        assertEquals(4, bytesBAOS[0]);
        assertEquals(78, bytesBAOS[1]);
        assertEquals(24, bytesBAOS[2]);
        assertEquals(56, bytesBAOS[3]);
        assertEquals(21, bytesBAOS[4]);
        assertEquals(44, bytesBAOS[5]);
        assertEquals(22, bytesBAOS[6]);
        assertEquals(37, bytesBAOS[7]);
    }

    @Test
    void testWriteFromArrayWithOffZeroANDLenLessArrayLength() throws IOException{
        byte[] byteTestArr = {4, 78};
        bufferedOutputStreamWithoutFile.write(byteTestArr, 0, byteTestArr.length);
        bufferedOutputStreamWithoutFile.flush();
        byte[] bytesBAOS = byteArrayOutputStreamWithoutFile.toByteArray();
        assertEquals(2, bytesBAOS.length);
        assertEquals(4, bytesBAOS[0]);
        assertEquals(78, bytesBAOS[1]);
    }

    @Test
    void testWriteFromArrayWithDifferentOffANDLen() throws IOException {

        byte[] byteTestArr = {4, 78, 24, 56, 21, 44, 22, 37};
        bufferedOutputStreamWithoutFile.write(byteTestArr, 0, 2);
        bufferedOutputStreamWithoutFile.write(byteTestArr, 2, 2);
        bufferedOutputStreamWithoutFile.write(byteTestArr, 4, 2);
        bufferedOutputStreamWithoutFile.flush();
        byte[] bytesBAOS = byteArrayOutputStreamWithoutFile.toByteArray();
        assertEquals(6, bytesBAOS.length);
        assertEquals(4, bytesBAOS[0]);
        assertEquals(78, bytesBAOS[1]);
        assertEquals(24, bytesBAOS[2]);
        assertEquals(56, bytesBAOS[3]);
        assertEquals(21, bytesBAOS[4]);
        assertEquals(44, bytesBAOS[5]);

        bufferedOutputStreamWithoutFile.write(byteTestArr, 4, 4);
        bufferedOutputStreamWithoutFile.flush();
        bytesBAOS = byteArrayOutputStreamWithoutFile.toByteArray();
        assertEquals(10, bytesBAOS.length);
        assertEquals(21, bytesBAOS[6]);
        assertEquals(44, bytesBAOS[7]);
        assertEquals(22, bytesBAOS[8]);
        assertEquals(37, bytesBAOS[9]);

    }

    @Test
    void testWriteFromArrayWithoutOffANDLenFILE() {
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
    void testWriteFromArrayWithOffZeroANDLenLessArrayLengthFILE() {
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

    @AfterEach
    void after() throws IOException {
        bufferedOutputStreamWithoutFile.close();
    }
}