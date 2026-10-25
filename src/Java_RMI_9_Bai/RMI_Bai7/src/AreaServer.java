import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class AreaServer extends UnicastRemoteObject implements AreaInterface {
    public AreaServer() throws RemoteException { super(); }

    @Override
    public double rectangleArea(double width, double height) throws RemoteException {
        if (width < 0 || height < 0) throw new IllegalArgumentException("Kich thuoc khong duoc am");
        return width * height;
    }

    @Override
    public double circleArea(double radius) throws RemoteException {
        if (radius < 0) throw new IllegalArgumentException("Ban kinh khong duoc am");
        return Math.PI * radius * radius;
    }

    @Override
    public double triangleArea(double base, double height) throws RemoteException {
        if (base < 0 || height < 0) throw new IllegalArgumentException("Kich thuoc khong duoc am");
        return base * height / 2;
    }

    public static void main(String[] args) {
        try {
            AreaServer server = new AreaServer();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("AreaService", server);
            System.out.println("Area Server dang chay...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
