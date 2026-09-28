package lab3;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class InventoryManager {

    
    static ArrayList<Product> danhSach = new ArrayList<Product>();

    static String FILE_NAME = "data/inventory.csv";
    static String REPORT_NAME = "data/inventory-report.txt";


    public static void nhapSanPham(Scanner sc) {

        System.out.print("Nhập mã sản phẩm: ");
        String ma = sc.nextLine();

        while (ma.trim().isEmpty()) {
            System.out.println("Lỗi: Mã sản phẩm không được rỗng!");
            System.out.print("Nhập lại mã: ");
            ma = sc.nextLine();
        }

        System.out.print("Nhập tên sản phẩm: ");
        String ten = sc.nextLine();

        while (ten.trim().isEmpty()) {
            System.out.println("Lỗi: Tên sản phẩm không được rỗng!");
            System.out.print("Nhập lại tên: ");
            ten = sc.nextLine();
        }

        double donGia;

        while (true) {
            System.out.print("Nhập đơn giá: ");

            try {
                donGia = Double.parseDouble(sc.nextLine());

                if (donGia > 0) {
                    break;
                }

                System.out.println("Lỗi: Đơn giá phải lớn hơn 0!");

            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Đơn giá phải là số!");
            }
        }

        int soLuong;

        while (true) {
            System.out.print("Nhập số lượng: ");

            try {
                soLuong = Integer.parseInt(sc.nextLine());

                if (soLuong >= 0) {
                    break;
                }

                System.out.println("Lỗi: Số lượng không được âm!");

            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Số lượng phải là số nguyên!");
            }
        }

        Product sp = new Product(ma, ten, donGia, soLuong);

        danhSach.add(sp);

        System.out.println("=> Thêm sản phẩm thành công!");
    }



    public static void luuFile() {

        Path path = Paths.get(FILE_NAME);

        try {
          
            Files.createDirectories(path.getParent());

            try (BufferedWriter writer = Files.newBufferedWriter(
                    path,
                    StandardCharsets.UTF_8)) {

               
                writer.write("ma,ten,donGia,soLuong");
                writer.newLine();

             
                for (Product sp : danhSach) {

                    writer.write(
                        sp.getMa() + "," +
                        sp.getTen() + "," +
                        sp.getDonGia() + "," +
                        sp.getSoLuong()
                    );

                    writer.newLine();
                }

            }

            System.out.println("Đã lưu file: " + FILE_NAME);

        } catch (IOException e) {

            System.out.println(
                "Lỗi khi ghi file " + FILE_NAME +
                ": " + e.getMessage()
            );
        }
    }


  
    public static void docFile() {

        Path path = Paths.get(FILE_NAME);


        danhSach.clear();

        try (BufferedReader reader = Files.newBufferedReader(
                path,
                StandardCharsets.UTF_8)) {

            String line;
            int soDong = 0;

            while ((line = reader.readLine()) != null) {

                soDong++;

                
                if (soDong == 1) {
                    continue;
                }

                if (line.trim().isEmpty()) {
                    continue;
                }

              
                String[] parts = line.split(",", -1);

               
                if (parts.length != 4) {

                    System.out.println(
                        "Lỗi file " + FILE_NAME +
                        " - dòng " + soDong +
                        ": thiếu hoặc thừa cột. Bỏ qua dòng."
                    );

                    continue;
                }

                String ma = parts[0].trim();
                String ten = parts[1].trim();

                
                if (ma.isEmpty() || ten.isEmpty()) {

                    System.out.println(
                        "Lỗi file " + FILE_NAME +
                        " - dòng " + soDong +
                        ": mã hoặc tên rỗng. Bỏ qua dòng."
                    );

                    continue;
                }

                try {

                    double donGia = Double.parseDouble(parts[2].trim());
                    int soLuong = Integer.parseInt(parts[3].trim());

                  
                    if (donGia <= 0) {

                        System.out.println(
                            "Lỗi file " + FILE_NAME +
                            " - dòng " + soDong +
                            ": đơn giá phải lớn hơn 0."
                        );

                        continue;
                    }

                    if (soLuong < 0) {

                        System.out.println(
                            "Lỗi file " + FILE_NAME +
                            " - dòng " + soDong +
                            ": số lượng không được âm."
                        );

                        continue;
                    }

                    Product sp = new Product(
                        ma,
                        ten,
                        donGia,
                        soLuong
                    );

                    danhSach.add(sp);

                } catch (NumberFormatException e) {

                    System.out.println(
                        "Lỗi file " + FILE_NAME +
                        " - dòng " + soDong +
                        ": dữ liệu số không hợp lệ."
                    );
                }
            }

            System.out.println(
                "Đã đọc file thành công: " + FILE_NAME
            );

        } catch (NoSuchFileException e) {

            System.out.println(
                "Không tìm thấy tệp: " + FILE_NAME
            );

        } catch (IOException e) {

            System.out.println(
                "Lỗi khi đọc file " + FILE_NAME +
                ": " + e.getMessage()
            );
        }
    }


    public static void hienThiDanhSach() {

        System.out.println("\n===== DANH SÁCH SẢN PHẨM =====");

        if (danhSach.isEmpty()) {
            System.out.println("Danh sách đang trống.");
            return;
        }

        for (Product sp : danhSach) {
            sp.hienThi();
        }
    }



    public static double tinhTongGiaTri() {

        double tong = 0;

        for (Product sp : danhSach) {
            tong = tong + sp.tinhGiaTriTonKho();
        }

        return tong;
    }



    public static Product timCaoNhat() {

        if (danhSach.isEmpty()) {
            return null;
        }

        Product max = danhSach.get(0);

        for (Product sp : danhSach) {

            if (sp.tinhGiaTriTonKho()
                    > max.tinhGiaTriTonKho()) {

                max = sp;
            }
        }

        return max;
    }



    public static void ghiBaoCao() {

        Path path = Paths.get(REPORT_NAME);

        try {

            Files.createDirectories(path.getParent());

            try (BufferedWriter writer = Files.newBufferedWriter(
                    path,
                    StandardCharsets.UTF_8)) {

                writer.write("===== BÁO CÁO TỒN KHO =====");
                writer.newLine();
                writer.newLine();

                writer.write("Danh sách sản phẩm:");
                writer.newLine();

                for (Product sp : danhSach) {

                    writer.write(
                        sp.getMa() + " | " +
                        sp.getTen() + " | " +
                        "Đơn giá: " + sp.getDonGia() + " | " +
                        "Số lượng: " + sp.getSoLuong() + " | " +
                        "Giá trị: " +
                        sp.tinhGiaTriTonKho()
                    );

                    writer.newLine();
                }

                writer.newLine();

                writer.write(
                    "Tổng giá trị tồn kho: " +
                    tinhTongGiaTri()
                );

                writer.newLine();

                Product max = timCaoNhat();

                if (max != null) {

                    writer.write(
                        "Sản phẩm có giá trị tồn kho cao nhất: " +
                        max.getMa() + " - " +
                        max.getTen() + " - " +
                        max.tinhGiaTriTonKho()
                    );

                    writer.newLine();
                }
            }

            System.out.println(
                "Đã ghi báo cáo: " + REPORT_NAME
            );

        } catch (IOException e) {

            System.out.println(
                "Lỗi khi ghi báo cáo " +
                REPORT_NAME + ": " +
                e.getMessage()
            );
        }
    }


  
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println(" INVENTORY MANAGER ");

        System.out.print("Nhập số lượng sản phẩm: ");

        int n;

        while (true) {

            try {

                n = Integer.parseInt(sc.nextLine());

                if (n > 0) {
                    break;
                }

                System.out.println(
                    "Số lượng sản phẩm phải > 0!"
                );

            } catch (NumberFormatException e) {

                System.out.println(
                    "Vui lòng nhập số nguyên!"
                );
            }
        }


     
        for (int i = 0; i < n; i++) {

            System.out.println(
                " Sản phẩm " + (i + 1) + ""
            );

            nhapSanPham(sc);
        }


        hienThiDanhSach();



        System.out.println(
            "\nTổng giá trị tồn kho: " +
            tinhTongGiaTri()
        );



        Product max = timCaoNhat();

        if (max != null) {

            System.out.println(
                " SẢN PHẨM GIÁ TRỊ CAO NHẤT"
            );

            max.hienThi();
        }


  
        System.out.println(" LƯU FILE ");
        luuFile();



        System.out.println(" ĐỌC LẠI FILE ");
        docFile();

        hienThiDanhSach();


   
        System.out.println("GHI BÁO CÁO ");
        ghiBaoCao();


        sc.close();
    }
}