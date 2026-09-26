package Lap2;

public class SinhVien extends Nguoi1 {

    private String maSinhVien;
    private String nganhHoc;
    private double diemTrungBinh;

    // Constructor gọi constructor lớp cha bằng super
    public SinhVien(String hoTen, int namSinh, String diaChi,
                     String maSinhVien, String nganhHoc, double diemTrungBinh) {
        super(hoTen, namSinh, diaChi);
        this.maSinhVien = maSinhVien;
        this.nganhHoc = nganhHoc;
        this.diemTrungBinh = diemTrungBinh;
    }

    // Getter và Setter
    public String getMaSinhVien() {
        return maSinhVien;
    }

    public void setMaSinhVien(String maSinhVien) {
        this.maSinhVien = maSinhVien;
    }

    public String getNganhHoc() {
        return nganhHoc;
    }

    public void setNganhHoc(String nganhHoc) {
        this.nganhHoc = nganhHoc;
    }

    public double getDiemTrungBinh() {
        return diemTrungBinh;
    }

    public void setDiemTrungBinh(double diemTrungBinh) {
        this.diemTrungBinh = diemTrungBinh;
    }

    // Xếp loại theo điểm trung bình
    public String xepLoai() {
        if (diemTrungBinh >= 8.5) {
            return "Gioi";
        } else if (diemTrungBinh >= 7.0) {
            return "Kha";
        } else if (diemTrungBinh >= 5.0) {
            return "Trung binh";
        } else {
            return "Yeu";
        }
    }

    // Ghi đè hienThiThongTin(), có gọi super
    @Override
    public void hienThiThongTin() {
        super.hienThiThongTin();
        System.out.println("Ma sinh vien: " + maSinhVien);
        System.out.println("Nganh hoc: " + nganhHoc);
        System.out.println("Diem trung binh: " + diemTrungBinh);
        System.out.println("Xep loai: " + xepLoai());
    }
}