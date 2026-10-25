import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

public class FlightServer extends UnicastRemoteObject implements FlightInterface {
    private final HashMap<String, Integer> available = new HashMap<>();
    private final HashMap<String, Integer> booked = new HashMap<>();

    public FlightServer() throws RemoteException {
        super();
        available.put("VN001", 100);
        available.put("VN002", 80);
        booked.put("VN001", 0);
        booked.put("VN002", 0);
    }

    @Override
    public synchronized boolean bookTicket(String flightNumber, int seats) throws RemoteException {
        if (seats <= 0 || !available.containsKey(flightNumber)) return false;
        int remaining = available.get(flightNumber);
        if (seats > remaining) return false;
        available.put(flightNumber, remaining - seats);
        booked.put(flightNumber, booked.get(flightNumber) + seats);
        System.out.println("Dat " + seats + " ghe cho " + flightNumber);
        return true;
    }

    @Override
    public synchronized int availableSeats(String flightNumber) throws RemoteException {
        return available.containsKey(flightNumber) ? available.get(flightNumber) : -1;
    }

    @Override
    public synchronized boolean cancelBooking(String flightNumber, int seats) throws RemoteException {
        if (seats <= 0 || !booked.containsKey(flightNumber)) return false;
        int currentBooked = booked.get(flightNumber);
        if (seats > currentBooked) return false;
        booked.put(flightNumber, currentBooked - seats);
        available.put(flightNumber, available.get(flightNumber) + seats);
        System.out.println("Huy " + seats + " ghe cho " + flightNumber);
        return true;
    }

    public static void main(String[] args) {
        try {
            FlightServer server = new FlightServer();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("FlightService", server);
            System.out.println("Flight Server dang chay...");
            System.out.println("VN001: 100 ghe");
            System.out.println("VN002: 80 ghe");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
