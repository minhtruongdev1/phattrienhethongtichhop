package lab3;

public class Product {

    // Đóng gói bằng private
    private String ma;
    private String ten;
    private double donGia;
    private int soLuong;

    // Constructor đầy đủ tham số
    public Product(String ma, String ten, double donGia, int soLuong) {
        this.ma = ma;
        this.ten = ten;
        this.donGia = donGia;
        this.soLuong = soLuong;
    }

    // Getter
    public String getMa() {
        return ma;
    }

    public String getTen() {
        return ten;
    }

    public double getDonGia() {
        return donGia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    // Tính giá trị tồn kho của sản phẩm
    public double tinhGiaTriTonKho() {
        return donGia * soLuong;
    }

    // Hiển thị
    public void hienThi() {
        System.out.println(
            ma + " | " + ten +
            " | Đơn giá: " + donGia +
            " | Số lượng: " + soLuong +
            " | Giá trị: " + tinhGiaTriTonKho()
        );
    }
}