package demo_tcp;
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class MultiClient {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 6000;

        try (
            Socket socket = new Socket(host, port);

            BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                    socket.getInputStream(),
                    StandardCharsets.UTF_8
                )
            );

            PrintWriter writer = new PrintWriter(
                new OutputStreamWriter(
                    socket.getOutputStream(),
                    StandardCharsets.UTF_8
                ),
                true
            );

            Scanner scanner = new Scanner(System.in)
        ) {
            while (true) {
                System.out.print("Nhập nội dung: ");
                String message = scanner.nextLine();

                writer.println(message);

                String response = reader.readLine();
                System.out.println(response);

                if (message.equalsIgnoreCase("exit")) {
                    break;
                }
            }

        } catch (IOException e) {
            System.out.println("Lỗi client: " + e.getMessage());
        }
    }
}