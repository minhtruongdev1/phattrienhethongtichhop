import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class AddClient {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            AddInterface service = (AddInterface) registry.lookup("AddService");
            Scanner sc = new Scanner(System.in);
            System.out.print("Nhap a: ");
            int a = sc.nextInt();
            System.out.print("Nhap b: ");
            int b = sc.nextInt();
            System.out.println("Tong = " + service.add(a, b));
            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
