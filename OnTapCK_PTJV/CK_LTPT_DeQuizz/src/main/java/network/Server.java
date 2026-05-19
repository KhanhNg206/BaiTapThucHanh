package network;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try(
                ServerSocket serverSocket = new ServerSocket(4741);
                ){
            System.out.println("Server đạng chạy");
            while (true){
                Socket socket = serverSocket.accept();
                System.out.println("Server đang chạy" + serverSocket.getInetAddress().getHostName());

                new Thread(new ClientHandle(socket)).start();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
