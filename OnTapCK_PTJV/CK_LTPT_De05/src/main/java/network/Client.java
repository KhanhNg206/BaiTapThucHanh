package network;

import dto.movieDTO;
import dto.showDTO;

import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try(Socket socket = new Socket("21AK22-COM",4741);
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            Scanner sc = new Scanner(System.in);
        ){
            System.out.println("Client đã chạy");

            while(true){
                System.out.println("0.thoát");
                System.out.println("1.test câu a");
                System.out.println("2.test câu b");
                System.out.println("3.test câu c");
                System.out.println("Vui lòng chọn : ");
                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice){
                    case 1 -> {
                        out.writeUTF("cauA");
                        System.out.println("Vui lòng nhập director : ");
                        String director = sc.nextLine();
                        out.writeUTF(director);
                        out.flush();
                        List<showDTO> showList = (List<showDTO>) in.readObject();
                        showList.forEach(System.out::println);
                    }
                    case 2 -> {
                        out.writeUTF("cauB");
                        System.out.println("Vui lòng nhập mã show : ");
                        String showId = sc.nextLine();
                        out.writeUTF(showId);
                        out.flush();
                        boolean result = (boolean)in.readObject();
                        if(result) {
                            System.out.println("Thành công");
                        }else  System.out.println("thất bại");
                    }
                    case 3 ->{
                        out.writeUTF("cauC");
                        System.out.println("đang thêm 1 movie mới");
                        out.flush();
                        boolean result = (boolean)in.readObject();
                        if(result) {
                            System.out.println("Thành công");
                            System.out.println(in.readUTF());
                        }else  System.out.println("thất bại");

                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
