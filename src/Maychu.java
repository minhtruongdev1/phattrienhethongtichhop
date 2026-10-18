package gk;

import java.io.*;
import java.net.*;
import java.util.*;

public class Maychu {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(7000);
        System.out.println("May chu dang lang nghe tai cong 7000...");
        while (true) {
            Socket socket = serverSocket.accept();
            new Thread(new Xulykhach(socket)).start(); // moi client mot luong
        }
    }

    static class Xulykhach implements Runnable {
        private final Socket socket;

        public Xulykhach(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
                 PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true)) {

                while (true) {
                    // Gui menu dich vu
                    out.println("1. Dao nguoc toan bo chuoi, in hoa chu dau moi tu");
                    out.println("2. Dao nguoc tung tu, in hoa chu dau");
                    out.println("3. Dem tong so tu cua tat ca cac dong");
                    out.println("4. Dem so tu tung dong");
                    out.println("5. Tinh tong va hieu hai so tren moi dong");
                    out.println("END");

                    String luaChon = in.readLine();
                    if (luaChon == null || luaChon.equals("EXIT.")) break;

                    // Nhan du lieu den khi gap dong "."
                    List<String> dsDong = new ArrayList<>();
                    String dong;
                    while ((dong = in.readLine()) != null && !dong.equals(".")) {
                        dsDong.add(dong);
                    }

                    // Xu ly dich vu
                    List<String> ketQua = new ArrayList<>();
                    switch (luaChon.trim()) {
                        case "1":
                            for (String d : dsDong)
                                ketQua.add(vietHoaMoiTu(new StringBuilder(d).reverse().toString()));
                            break;
                        case "2":
                            for (String d : dsDong) ketQua.add(daoNguocTungTu(d));
                            break;
                        case "3":
                            int tong = 0;
                            for (String d : dsDong) tong += demTu(d);
                            ketQua.add("Tong so tu: " + tong);
                            break;
                        case "4":
                            for (String d : dsDong) ketQua.add(demTu(d) + " tu");
                            break;
                        case "5":
                            for (String d : dsDong) ketQua.add(tongVaHieu(d));
                            break;
                        default:
                            ketQua.add("Lua chon khong hop le");
                    }

                    for (String kq : ketQua) out.println(kq);
                    out.println("END");
                }
            } catch (IOException e) {
                System.out.println("Client ngat ket noi.");
            } finally {
                try { socket.close(); } catch (IOException ignored) {}
            }
        }

        static String vietHoaTu(String tu) {
            if (tu.isEmpty()) return tu;
            return Character.toUpperCase(tu.charAt(0)) + tu.substring(1).toLowerCase();
        }

        static String vietHoaMoiTu(String chuoi) {
            StringBuilder sb = new StringBuilder();
            for (String tu : chuoi.trim().split("\\s+")) {
                if (tu.isEmpty()) continue;
                if (sb.length() > 0) sb.append(' ');
                sb.append(vietHoaTu(tu));
            }
            return sb.toString();
        }

        static String daoNguocTungTu(String chuoi) {
            StringBuilder sb = new StringBuilder();
            for (String tu : chuoi.trim().split("\\s+")) {
                if (tu.isEmpty()) continue;
                if (sb.length() > 0) sb.append(' ');
                sb.append(vietHoaTu(new StringBuilder(tu).reverse().toString()));
            }
            return sb.toString();
        }

        static int demTu(String chuoi) {
            String t = chuoi.trim();
            return t.isEmpty() ? 0 : t.split("\\s+").length;
        }

        static String tongVaHieu(String chuoi) {
            String[] phan = chuoi.trim().split("\\s+");
            if (phan.length != 2) return "Dong khong hop le: " + chuoi;
            try {
                long a = Long.parseLong(phan[0]);
                long b = Long.parseLong(phan[1]);
                return "Tong = " + (a + b) + ", Hieu = " + (a - b);
            } catch (NumberFormatException e) {
                return "Dong khong hop le: " + chuoi;
            }
        }
    }
}
