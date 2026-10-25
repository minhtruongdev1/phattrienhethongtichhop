import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class ChatClient {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            ChatInterface service = (ChatInterface) registry.lookup("ChatService");
            Scanner sc = new Scanner(System.in);
            while (true) {
                System.out.print("Ban: ");
                String message = sc.nextLine();
                service.sendMessage(message);
                System.out.println(service.getResponse());
                if (message.equalsIgnoreCase("bye")) break;
            }
            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
