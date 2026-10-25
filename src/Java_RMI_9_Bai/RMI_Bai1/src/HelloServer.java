import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class HelloServer extends UnicastRemoteObject implements HelloInterface {
    public HelloServer() throws RemoteException { super(); }

    @Override
    public String sayHello() throws RemoteException {
        return "Hello, World!";
    }

    public static void main(String[] args) {
        try {
            HelloServer server = new HelloServer();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("HelloService", server);
            System.out.println("Hello Server dang chay...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
