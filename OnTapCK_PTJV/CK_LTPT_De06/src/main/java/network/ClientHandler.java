package network;

import service.bookService;
import service.impl.bookServiceImpl;

import java.io.DataInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientHandler implements Runnable{
    private Socket socket;
    private bookService service;

    public ClientHandler(Socket socket) {
        this.socket = socket;
        this.service = new bookServiceImpl();
    }

    @Override
    public void run() {
        try(
                DataInputStream in = new DataInputStream(socket.getInputStream());
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                ){

            while(true){
                String command = in.readUTF();
                switch(command){
                    case "cauA" -> {
                        String authorName = in.readUTF();
                        int rating = in.readInt();
                        out.writeObject(service.listRateBooks(authorName,rating));
                        out.flush();
                    }
                    case "cauB" -> {
                        out.writeObject(service.countBooksByAuthor());
                        out.flush();
                    }
                    case "cauC" -> {
                        String isbn = in.readUTF();
                        String readerId = in.readUTF();
                        int rating = in.readInt();
                        String commment = in.readUTF();
                        out.writeObject(service.updateReviews(isbn,readerId,rating,commment));
                        out.flush();
                    }
                    default -> {

                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
