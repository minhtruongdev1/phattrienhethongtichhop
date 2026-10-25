import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class BankClient {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            BankInterface service = (BankInterface) registry.lookup("BankService");
            Scanner sc = new Scanner(System.in);
            int choice;
            do {
                System.out.println("\n===== NGAN HANG =====");
                System.out.println("1. Xem so du");
                System.out.println("2. Gui tien");
                System.out.println("3. Rut tien");
                System.out.println("0. Thoat");
                System.out.print("Chon: ");
                choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 1:
                        System.out.printf("So du: %.0f VND%n", service.getBalance());
                        break;
                    case 2:
                        System.out.print("Nhap so tien gui: ");
                        double deposit = Double.parseDouble(sc.nextLine());
                        try {
                            service.deposit(deposit);
                            System.out.println("Gui tien thanh cong.");
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 3:
                        System.out.print("Nhap so tien rut: ");
                        double withdraw = Double.parseDouble(sc.nextLine());
                        System.out.println(service.withdraw(withdraw) ? "Rut tien thanh cong." : "So tien khong hop le hoac khong du so du.");
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
