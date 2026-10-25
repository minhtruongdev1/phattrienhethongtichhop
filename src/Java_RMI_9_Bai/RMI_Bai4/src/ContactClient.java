import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class ContactClient {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            ContactInterface service = (ContactInterface) registry.lookup("ContactService");
            Scanner sc = new Scanner(System.in);
            int choice;
            do {
                System.out.println("\n===== QUAN LY DANH BA =====");
                System.out.println("1. Them lien he");
                System.out.println("2. Tim lien he");
                System.out.println("3. Sua so dien thoai");
                System.out.println("4. Xoa lien he");
                System.out.println("0. Thoat");
                System.out.print("Chon: ");
                choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 1:
                        System.out.print("Nhap ten: ");
                        String nameAdd = sc.nextLine();
                        System.out.print("Nhap SDT: ");
                        String phoneAdd = sc.nextLine();
                        service.addContact(nameAdd, phoneAdd);
                        System.out.println("Da them lien he.");
                        break;
                    case 2:
                        System.out.print("Nhap ten can tim: ");
                        String nameFind = sc.nextLine();
                        String phone = service.findContact(nameFind);
                        System.out.println(phone == null ? "Khong tim thay." : "Ten: " + nameFind + ", SDT: " + phone);
                        break;
                    case 3:
                        System.out.print("Nhap ten can sua: ");
                        String nameUpdate = sc.nextLine();
                        System.out.print("Nhap SDT moi: ");
                        String newPhone = sc.nextLine();
                        System.out.println(service.updateContact(nameUpdate, newPhone) ? "Da cap nhat." : "Khong tim thay lien he.");
                        break;
                    case 4:
                        System.out.print("Nhap ten can xoa: ");
                        String nameDelete = sc.nextLine();
                        System.out.println(service.deleteContact(nameDelete) ? "Da xoa." : "Khong tim thay lien he.");
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
