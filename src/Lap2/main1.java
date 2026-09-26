package Lap2;

public class main1 {
    public static void main(String[] args) {

        // Tao hai sinh vien
        SinhVien sv1 = new SinhVien("Nguyen Van A", 2003, "TP.HCM",
                "SV001", "Cong nghe thong tin", 8.7);
        SinhVien sv2 = new SinhVien("Tran Thi B", 2004, "Dong Nai",
                "SV002", "Ke toan", 6.5);

        // Tao hai giang vien
        GiangVien gv1 = new GiangVien("Le Van C", 1980, "TP.HCM",
                "GV001", "Cong nghe thong tin", 8000000, 3.5);
        GiangVien gv2 = new GiangVien("Pham Thi D", 1975, "TP.HCM",
                "GV002", "Toan hoc", 9000000, 4.0);

        System.out.println("===== THONG TIN SINH VIEN =====");
        System.out.println("\n--- Sinh vien 1 ---");
        sv1.hienThiThongTin();
        System.out.println("\n--- Sinh vien 2 ---");
        sv2.hienThiThongTin();

        System.out.println("\n===== THONG TIN GIANG VIEN =====");
        System.out.println("\n--- Giang vien 1 ---");
        gv1.hienThiThongTin();
        System.out.println("\n--- Giang vien 2 ---");
        gv2.hienThiThongTin();
    }
}