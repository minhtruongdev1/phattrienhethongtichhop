package lab01;

import java.io.File;

public class DeleteFileIO {
	private void deleteFile(String source) {
		//new file
		File file = new File(source);
		//check file exist
		// neu ton tai
		if(file.exists()) {
		System.out.println("file ton tai");
		file.delete();
		System.out.print("xoa file thanh cong");
		}
		else {
		System.out.println("file khong ton tai");
		}
		}
		public static void main(String[] args) {
		DeleteFileIO deleteFileIO = new DeleteFileIO();
		deleteFileIO.deleteFile("D:/HocJava/demo.txt");
		}
}
