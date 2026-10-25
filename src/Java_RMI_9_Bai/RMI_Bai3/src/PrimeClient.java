import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class PrimeClient {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            PrimeInterface service = (PrimeInterface) registry.lookup("PrimeService");
            Scanner sc = new Scanner(System.in);
            System.out.print("Nhap so n: ");
            int n = sc.nextInt();
            System.out.println(n + (service.isPrime(n) ? " la so nguyen to" : " khong la so nguyen to"));
            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
