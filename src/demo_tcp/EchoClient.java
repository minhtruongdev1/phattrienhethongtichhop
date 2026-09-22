package demo_tcp;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class EchoClient {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 5000;

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
            System.out.print("Nhập nội dung gửi server: ");
            String message = scanner.nextLine();

            writer.println(message);

            String response = reader.readLine();
            System.out.println("Server phản hồi: " + response);

        } catch (IOException e) {
            System.out.println("Lỗi client: " + e.getMessage());
        }
    }
}