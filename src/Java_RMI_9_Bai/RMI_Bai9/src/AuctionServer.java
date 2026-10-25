import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

public class AuctionServer extends UnicastRemoteObject implements AuctionInterface {
    private static class Product {
        String name;
        long startingPrice;
        long currentPrice;
        String highestBidder;

        Product(String name, long startingPrice) {
            this.name = name;
            this.startingPrice = startingPrice;
            this.currentPrice = startingPrice;
            this.highestBidder = "Chua co";
        }
    }

    private final HashMap<String, Product> products = new HashMap<>();

    public AuctionServer() throws RemoteException { super(); }

    @Override
    public synchronized boolean addProduct(String name, long startingPrice) throws RemoteException {
        if (name == null || name.trim().isEmpty() || startingPrice <= 0 || products.containsKey(name)) {
            return false;
        }
        products.put(name, new Product(name, startingPrice));
        System.out.println("Da them san pham: " + name);
        return true;
    }

    @Override
    public synchronized String[] listProducts() throws RemoteException {
        String[] result = new String[products.size()];
        int i = 0;
        for (Product p : products.values()) {
            result[i++] = "San pham: " + p.name
                    + " | Gia khoi diem: " + p.startingPrice
                    + " | Gia hien tai: " + p.currentPrice
                    + " | Nguoi dat cao nhat: " + p.highestBidder;
        }
        return result;
    }

    @Override
    public synchronized String placeBid(String name, long amount) throws RemoteException {
        Product p = products.get(name);
        if (p == null) return "Khong tim thay san pham.";
        if (amount <= p.currentPrice) return "Gia thau phai cao hon gia hien tai: " + p.currentPrice;
        p.currentPrice = amount;
        p.highestBidder = "Bidder";
        String message = "Co gia thau moi cho " + p.name + ". Gia cao nhat: " + p.currentPrice;
        System.out.println(message);
        return "Dat gia thanh cong! " + message;
    }

    public static void main(String[] args) {
        try {
            AuctionServer server = new AuctionServer();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("AuctionService", server);
            System.out.println("Auction Server dang chay...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
