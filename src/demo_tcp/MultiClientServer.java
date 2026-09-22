package demo_tcp;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiClientServer {
    public static void main(String[] args) {
        int port = 6000;

        ExecutorService threadPool =
                Executors.newFixedThreadPool(3);

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server đang chạy tại port " + port);

            while (true) {
                Socket socket = serverSocket.accept();

                System.out.println("Có client mới kết nối");

                // Giao socket cho worker xử lý
                threadPool.execute(() -> handleClient(socket));
            }

        } catch (IOException e) {
            System.out.println("Lỗi server: " + e.getMessage());
        } finally {
            threadPool.shutdown();
        }
    }

    public static void handleClient(Socket socket) {
        try (
            socket;

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
            String message;

            while ((message = reader.readLine()) != null) {
                System.out.println("Client gửi: " + message);

                if (message.equalsIgnoreCase("exit")) {
                    writer.println("Đã đóng kết nối");
                    break;
                }

                writer.println("Server phản hồi: " + message.toUpperCase());
            }

        } catch (IOException e) {
            System.out.println(
                "Một client bị lỗi hoặc đã ngắt kết nối: "
                + e.getMessage()
            );
        }
    }
}