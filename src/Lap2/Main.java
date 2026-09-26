package Lap2;

public class Main {
    public static void main(String[] args) {
        SanPham sp1 = new SanPham("SP01", "Laptop", 1.5E7, 10);
        SanPham sp2 = new SanPham("SP02", "Chuot", 300000, 20);

        System.out.println(" THONG TIN BAN DAU ");
        System.out.println("San pham 1 ");
        sp1.hienThiThongTin();
        System.out.println(" San pham 2 ");
        sp2.hienThiThongTin();

        System.out.println(" NHAP THEM HANG ");
        System.out.println("Truoc khi nhap:");
        sp1.hienThiThongTin();
        sp1.nhapHang(5);
        System.out.println("Sau khi nhap 5 san pham:");
        sp1.hienThiThongTin();

        System.out.println("BAN HANG THANH CONG ");
        System.out.println("Truoc khi ban:");
        sp1.hienThiThongTin();
        boolean ketQua1 = sp1.banHang(8);
        System.out.println(ketQua1 ? "Ban hang thanh cong!" : "Ban hang that bai!");
        System.out.println("\nSau khi ban:");
        sp1.hienThiThongTin();

        System.out.println("BAN QUA SO LUONG TON KHO ");
        System.out.println("Truoc khi ban:");
        sp1.hienThiThongTin();
        boolean ketQua2 = sp1.banHang(20);
        System.out.println(ketQua2 ? "Ban hang thanh cong!" : "Ban hang that bai! Khong du hang.");
        System.out.println("Sau khi ban:");
        sp1.hienThiThongTin();
    }
}
