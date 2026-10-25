import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class PrimeServer extends UnicastRemoteObject implements PrimeInterface {
    public PrimeServer() throws RemoteException { super(); }

    @Override
    public boolean isPrime(int n) throws RemoteException {
        if (n < 2) return false;
        for (int i = 2; i <= n / i; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        try {
            PrimeServer server = new PrimeServer();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("PrimeService", server);
            System.out.println("Prime Server dang chay...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
