import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class AddServer extends UnicastRemoteObject implements AddInterface {
    public AddServer() throws RemoteException { super(); }

    @Override
    public int add(int a, int b) throws RemoteException {
        return a + b;
    }

    public static void main(String[] args) {
        try {
            AddServer server = new AddServer();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("AddService", server);
            System.out.println("Add Server dang chay...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
