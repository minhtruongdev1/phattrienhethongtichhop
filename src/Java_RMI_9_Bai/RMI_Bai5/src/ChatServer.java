import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ChatServer extends UnicastRemoteObject implements ChatInterface {
    private String response = "Xin chao! Toi la Server.";

    public ChatServer() throws RemoteException { super(); }

    @Override
    public synchronized void sendMessage(String message) throws RemoteException {
        System.out.println("Client: " + message);
        if (message.equalsIgnoreCase("hello")) {
            response = "Server: Xin chao ban!";
        } else if (message.equalsIgnoreCase("bye")) {
            response = "Server: Tam biet ban!";
        } else {
            response = "Server da nhan: " + message;
        }
        System.out.println(response);
    }

    @Override
    public synchronized String getResponse() throws RemoteException {
        return response;
    }

    public static void main(String[] args) {
        try {
            ChatServer server = new ChatServer();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("ChatService", server);
            System.out.println("Chat Server dang chay...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
