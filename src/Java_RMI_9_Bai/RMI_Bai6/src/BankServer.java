import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class BankServer extends UnicastRemoteObject implements BankInterface {
    private double balance = 1000000;

    public BankServer() throws RemoteException { super(); }

    @Override
    public synchronized double getBalance() throws RemoteException {
        return balance;
    }

    @Override
    public synchronized void deposit(double amount) throws RemoteException {
        if (amount <= 0) throw new IllegalArgumentException("So tien gui phai lon hon 0");
        balance += amount;
    }

    @Override
    public synchronized boolean withdraw(double amount) throws RemoteException {
        if (amount <= 0 || amount > balance) return false;
        balance -= amount;
        return true;
    }

    public static void main(String[] args) {
        try {
            BankServer server = new BankServer();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("BankService", server);
            System.out.println("Bank Server dang chay...");
            System.out.println("So du ban dau: 1000000 VND");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
