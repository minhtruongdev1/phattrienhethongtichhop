package lab01;

import java.io.File;

public class findFile {

    public void findFile(String source, String key) {
        File file = new File(source);

        if (file.exists()) {

            if (file.isFile()) {
                if (file.getName().endsWith(key)) {
                    System.out.println(file.getAbsolutePath());
                }
            }

            File[] listFile = file.listFiles();

            if (listFile != null) {
                for (File f : listFile) {
                    findFile(f.getAbsolutePath(), key);
                }
            }

        } else {
            System.out.println("Source không tồn tại");
        }
    }
}