import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class AuctionClient {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            AuctionInterface service = (AuctionInterface) registry.lookup("AuctionService");
            Scanner sc = new Scanner(System.in);
            int choice;
            do {
                System.out.println("\n===== ONLINE AUCTION =====");
                System.out.println("1. Them san pham (Seller)");
                System.out.println("2. Xem danh sach san pham");
                System.out.println("3. Dat gia thau (Bidder)");
                System.out.println("0. Thoat");
                System.out.print("Chon: ");
                choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 1: {
                        System.out.print("Ten san pham: ");
                        String name = sc.nextLine();
                        System.out.print("Gia khoi diem: ");
                        long price = Long.parseLong(sc.nextLine());
                        System.out.println(service.addProduct(name, price) ? "Them san pham thanh cong." : "Them that bai. Kiem tra ten va gia.");
                        break;
                    }
                    case 2: {
                        String[] products = service.listProducts();
                        if (products.length == 0) {
                            System.out.println("Chua co san pham.");
                        } else {
                            for (String product : products) System.out.println(product);
                        }
                        break;
                    }
                    case 3: {
                        System.out.print("Ten san pham: ");
                        String name = sc.nextLine();
                        System.out.print("Gia muon dat: ");
                        long amount = Long.parseLong(sc.nextLine());
                        System.out.println(service.placeBid(name, amount));
                        break;
                    }
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
