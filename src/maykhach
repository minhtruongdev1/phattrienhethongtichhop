import java.io.*;
import java.net.*;
import java.util.Scanner;

public class MayKhach {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap IP may chu (127.0.0.1 neu test cung may): ");
        String ip = sc.nextLine();

        try (Socket socket = new Socket(ip, 7000);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
             PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true)) {

            while (true) {
                inDenEnd(in); // in menu dich vu

                System.out.print("Chon dich vu (1-4), go EXIT. de thoat: ");
                String luaChon = sc.nextLine().trim();
                out.println(luaChon);
                if (luaChon.equals("EXIT.")) break;

                System.out.println("Nhap cac dong, go '.' de ket thuc:");
                String dong;
                while (!(dong = sc.nextLine()).equals(".")) {
                    out.println(dong);
                }
                out.println(".");

                System.out.println("--- Ket qua ---");
                inDenEnd(in);
            }
        }
        System.out.println("Da dong ket noi.");
    }

    static void inDenEnd(BufferedReader in) throws IOException {
        String dong;
        while ((dong = in.readLine()) != null && !dong.equals("END")) {
            System.out.println(dong);
        }
    }
}
