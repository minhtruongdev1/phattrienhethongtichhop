import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AuctionInterface extends Remote {
    boolean addProduct(String name, long startingPrice) throws RemoteException;
    String[] listProducts() throws RemoteException;
    String placeBid(String name, long amount) throws RemoteException;
}
