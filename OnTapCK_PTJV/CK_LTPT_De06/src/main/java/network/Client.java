package network;

import dto.BookDTO;
import models.Reviews;

import java.io.DataOutputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("21AK22-COM",4741);
             ObjectInput in = new ObjectInputStream(socket.getInputStream());
             DataOutputStream out = new DataOutputStream(socket.getOutputStream());
             Scanner sc  = new Scanner(System.in);
        ) {

            System.out.println("Client đã khởi động");

            while(true){
                System.out.println("0. Thoát") ;
                System.out.println("1. test câu a");
                System.out.println("2. test câu b");
                System.out.println("3. test câu c");
                System.out.println("vui lòng chọn : ");
                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice){
                    case 1 -> {
                        out.writeUTF("cauA");
                        System.out.println("Nhập tên tác giả :");
                        String authorName = sc.nextLine();
                        System.out.println("Nhập rating tác giả :");
                        int rating = sc.nextInt();
                        sc.nextLine();
                        out.writeUTF(authorName);
                        out.writeInt(rating);
                        out.flush();
                        List<BookDTO> bookList = (List<BookDTO>) in.readObject();
                        bookList.forEach(System.out::println);
                    }
                    case 2 -> {
                        out.writeUTF("cauB");
                        out.flush();
                        Map<String,Long> rs = (Map<String,Long>) in.readObject();
                        rs.forEach((k,v) -> System.out.println(k + ": "+v));
                    }
                    case 3 -> {
                        out.writeUTF("cauC");

                        System.out.println("Nhập isbn:");
                        String isbn = sc.nextLine();

                        System.out.println("Nhập readerID:");
                        String readerID = sc.nextLine();

                        System.out.println("Nhập rating:");
                        int rating = sc.nextInt();
                        sc.nextLine();

                        System.out.println("Nhập comment:");
                        String comment = sc.nextLine();

                        out.writeUTF(isbn);
                        out.writeUTF(readerID);
                        out.writeInt(rating);
                        out.writeUTF(comment);
                        out.flush();
                        boolean result = (boolean) in.readObject();
                        if(result) System.out.println("thành công");
                        else System.out.println("thất bại");
                    }
                    case 0 -> {
                        socket.close();
                        System.out.println("Client đã mâ kết nối");
                        return;
                    }
                    default -> {
                        System.out.print("Lựa chọn không tồn tại, vui lòng chọn lại");
                    }
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
