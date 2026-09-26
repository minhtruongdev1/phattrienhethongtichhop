
package Lap2;

public class SanPham {

    // 1. Khai báo thuộc tính private
    private String maSanPham;
    private String tenSanPham;
    private double donGia;
    private int soLuong;

    // 2. Constructor đầy đủ tham số
    public SanPham(String maSanPham, String tenSanPham, double donGia, int soLuong) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.donGia = donGia;
        this.soLuong = soLuong;
    }

    // 3. Getter
    public String getMaSanPham() {
        return maSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public double getDonGia() {
        return donGia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    // 4. Tính thành tiền
    public double tinhThanhTien() {
        return donGia * soLuong;
    }

    // 5. Nhập hàng
    public void nhapHang(int soLuongNhap) {

        if (soLuongNhap > 0) {
            soLuong = soLuong + soLuongNhap;
            System.out.println("Nhập hàng thành công!");
        } else {
            System.out.println("Số lượng nhập phải lớn hơn 0!");
        }
    }

    // 6. Bán hàng
    public boolean banHang(int soLuongBan) {

        // Kiểm tra số lượng bán
        if (soLuongBan <= 0) {
            return false;
        }

        // Kiểm tra tồn kho
        if (soLuongBan > soLuong) {
            return false;
        }

        // Bán thành công thì trừ tồn kho
        soLuong = soLuong - soLuongBan;

        return true;
    }

    // 7. Hiển thị thông tin
    public void hienThiThongTin() {

        System.out.println("ma san pham" + maSanPham);
        System.out.println("ten san pham: " + tenSanPham);
        System.out.println("don gia : " + donGia);
        System.out.println("So luong ton kho: " + soLuong);
        System.out.println("thanh tien: " + tinhThanhTien());
    }
}

