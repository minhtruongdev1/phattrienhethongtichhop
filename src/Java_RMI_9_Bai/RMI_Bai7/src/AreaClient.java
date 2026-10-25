import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class AreaClient {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            AreaInterface service = (AreaInterface) registry.lookup("AreaService");
            Scanner sc = new Scanner(System.in);
            int choice;
            do {
                System.out.println("\n===== TINH DIEN TICH =====");
                System.out.println("1. Hinh chu nhat");
                System.out.println("2. Hinh tron");
                System.out.println("3. Hinh tam giac");
                System.out.println("0. Thoat");
                System.out.print("Chon: ");
                choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 1:
                        System.out.print("Nhap chieu rong: ");
                        double width = Double.parseDouble(sc.nextLine());
                        System.out.print("Nhap chieu cao: ");
                        double height = Double.parseDouble(sc.nextLine());
                        System.out.println("Dien tich = " + service.rectangleArea(width, height));
                        break;
                    case 2:
                        System.out.print("Nhap ban kinh: ");
                        double radius = Double.parseDouble(sc.nextLine());
                        System.out.println("Dien tich = " + service.circleArea(radius));
                        break;
                    case 3:
                        System.out.print("Nhap canh day: ");
                        double base = Double.parseDouble(sc.nextLine());
                        System.out.print("Nhap chieu cao: ");
                        double h = Double.parseDouble(sc.nextLine());
                        System.out.println("Dien tich = " + service.triangleArea(base, h));
                        break;
                    case 0:
                        System.out.println("Ket thuc.");
                        break;
                    default:
                        System.out.println("Lua chon khong hop le.");
                }
            } while (choice != 0);
            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
