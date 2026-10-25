import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class FlightClient {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            FlightInterface service = (FlightInterface) registry.lookup("FlightService");
            Scanner sc = new Scanner(System.in);
            int choice;
            do {
                System.out.println("\n===== DAT VE MAY BAY =====");
                System.out.println("1. Dat ve");
                System.out.println("2. Kiem tra ghe trong");
                System.out.println("3. Huy ve");
                System.out.println("0. Thoat");
                System.out.print("Chon: ");
                choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 1: {
                        System.out.print("Ma chuyen bay: ");
                        String flight = sc.nextLine();
                        System.out.print("So ghe can dat: ");
                        int seats = Integer.parseInt(sc.nextLine());
                        System.out.println(service.bookTicket(flight, seats) ? "Dat ve thanh cong." : "Dat ve that bai. Kiem tra ma bay, so ghe.");
                        break;
                    }
                    case 2: {
                        System.out.print("Ma chuyen bay: ");
                        String flight = sc.nextLine();
                        int remaining = service.availableSeats(flight);
                        System.out.println(remaining == -1 ? "Khong tim thay chuyen bay." : "So ghe con trong: " + remaining);
                        break;
                    }
                    case 3: {
                        System.out.print("Ma chuyen bay: ");
                        String flight = sc.nextLine();
                        System.out.print("So ghe can huy: ");
                        int seats = Integer.parseInt(sc.nextLine());
                        System.out.println(service.cancelBooking(flight, seats) ? "Huy ve thanh cong." : "Huy ve that bai. Kiem tra so ve da dat.");
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
