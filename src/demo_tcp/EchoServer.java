package demo_tcp;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class EchoServer {
    public static void main(String[] args) {
        int port = 5000;

        try (
            ServerSocket serverSocket = new ServerSocket(port)
        ) {
            System.out.println("Server đang chạy tại port " + port);
            System.out.println("Đang chờ client kết nối...");

            try (
                Socket socket = serverSocket.accept();

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
                )
            ) {
                System.out.println("Client đã kết nối!");

                String message = reader.readLine();
                System.out.println("Client gửi: " + message);

                writer.println("Server đã nhận: " + message);
            }

        } catch (IOException e) {
            System.out.println("Lỗi server: " + e.getMessage());
        }
    }
}