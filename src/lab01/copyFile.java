package lab01;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class copyFile {

    public boolean copyFile(String source, String dest)
            throws FileNotFoundException, IOException {


        File sourceFile = new File(source);


        File destFile = new File(dest);


        if (sourceFile.exists() && sourceFile.isFile()) {

            FileInputStream fis = new FileInputStream(sourceFile);
            FileOutputStream fos = new FileOutputStream(destFile);

            byte[] arr = new byte[1024];
            int length;

            while ((length = fis.read(arr)) != -1) {
                fos.write(arr, 0, length);
            }

            fos.flush();
            fis.close();
            fos.close();

            System.out.println("Copy thành công");
            return true;

        } else {
            System.out.println("File nguồn không tồn tại");
            return false;
        }
    }
}