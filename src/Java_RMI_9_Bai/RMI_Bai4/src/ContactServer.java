import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

public class ContactServer extends UnicastRemoteObject implements ContactInterface {
    private final HashMap<String, String> contacts;

    public ContactServer() throws RemoteException {
        super();
        contacts = new HashMap<>();
    }

    @Override
    public synchronized void addContact(String name, String phone) throws RemoteException {
        contacts.put(name, phone);
    }

    @Override
    public synchronized String findContact(String name) throws RemoteException {
        return contacts.get(name);
    }

    @Override
    public synchronized boolean deleteContact(String name) throws RemoteException {
        return contacts.remove(name) != null;
    }

    @Override
    public synchronized boolean updateContact(String name, String newPhone) throws RemoteException {
        if (!contacts.containsKey(name)) return false;
        contacts.put(name, newPhone);
        return true;
    }

    public static void main(String[] args) {
        try {
            ContactServer server = new ContactServer();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("ContactService", server);
            System.out.println("Contact Server dang chay...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
