package readwrite01;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class FileAccess {
    public void randAccessFile(String filePath) {
        try {
            RandomAccessFile raf = new RandomAccessFile(filePath, "rw");
            long stdId = 65130500066L;
            String name = "Phuwamet";
            String lastName = "Panjachalermrat";
            String address = "Bangkok";
            double gpa = 3.76;

            writeFile(raf, stdId, name, lastName, address, gpa);

            stdId = 65130500067L;
            name = "Tom";
            lastName = "Honlands";
            address = "London";
            gpa = 3.99;
            writeFile(raf, stdId, name, lastName, address, gpa);

            raf.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void writeFile(RandomAccessFile raf, long stdId, String name, String lastName, String address, double gpa) {
        try {
            raf.writeLong(stdId);
            byte[] nameInBytes = new byte[40];
            nameInBytes = getOverOfString(name, 40).getBytes();
            raf.write(nameInBytes, 0, 40);
            byte[] lastNameInBytes = new byte[50];
            lastNameInBytes = getOverOfString(lastName, 50).getBytes();
            raf.write(lastNameInBytes, 0, 50);
            byte[] addressInBytes = new byte[20];
            addressInBytes = getOverOfString(address, 20).getBytes();
            raf.write(addressInBytes, 0, 20);
            raf.writeDouble(gpa);
            raf.writeUTF("\n");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getOverOfString(String str, int length) {
        if (str.length() > length) {
            return str.substring(0, length);
        }
        for (int i = str.length(); i < length; i++) {
            str += " ";
        }
        return str;
    }

    public void readRaf(String filePath) {
        try {
            int pointerStdId = 0;
            int pointerName = 8;
            int pointerLastName = 48;
            int pointerAddress = 98;
            int pointerGpa = 118;
            RandomAccessFile raf = new RandomAccessFile(filePath, "r");

            raf.seek(pointerStdId);
            long stdId = raf.readLong();

            raf.seek(pointerName);
            byte[] nameInBytes = new byte[40];
            raf.read(nameInBytes, 0, 40);
            String name = new String(nameInBytes);

            raf.seek(pointerLastName);
            byte[] lastNameInBytes = new byte[50];
            raf.read(lastNameInBytes, 0, 50);
            String lastName = new String(lastNameInBytes);

            raf.seek(pointerAddress);
            byte[] addressInBytes = new byte[20];
            raf.read(addressInBytes, 0, 20);
            String address = new String(addressInBytes);

            raf.seek(pointerGpa);
            double gpa = raf.readDouble();

            System.out.println("stdId = " + stdId);
            System.out.println("name = " + name);
            System.out.println("lastName = " + lastName);
            System.out.println("size pf lastName = " + lastName.length());
            System.out.println("address = " + address);
            System.out.println("gpa = " + gpa);

            raf.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
