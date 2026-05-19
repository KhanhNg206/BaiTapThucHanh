package network;

import entity.Person;
import entity.Vaccine;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try(
                Socket socket = new Socket("21AK22-COM",4741);
                ObjectInput in = new ObjectInputStream(socket.getInputStream());
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                Scanner sc = new Scanner(System.in);
        ){
            System.out.println("Client đã chạy");
            while (true){
                System.out.println("0.thoát");
                System.out.println("1.test câu a");
                System.out.println("2.test câu b");
                System.out.println("3.test câu c");
                System.out.println("4.test câu d");
                int choice = sc.nextInt();
                sc.nextLine();
                switch (choice){
                    case 0 -> {
                        System.out.println("Server đã đóng");
                        socket.close();
                        return;
                    }
                    case 1 -> {
                        out.writeUTF("cauA");
                        out.flush();
                        Boolean result = (Boolean) in.readObject();
                        System.out.println(result);
                    }
                    case 2 -> {
                        out.writeUTF("cauB");
                        out.flush();
                        Boolean result = (Boolean) in.readObject();
                        System.out.println(result);
                    }
                    case 3 -> {
                        out.writeUTF("cauC");
                        out.flush();
                        List<Person> list = (List<Person>) in.readObject();
                        list.forEach(System.out::println);
                    }
                    case 4 -> {
                        out.writeUTF("cauD");
                        out.flush();
                        Map<Vaccine,Integer> map = (Map<Vaccine, Integer>) in.readObject();
                        map.forEach((k,v) -> System.out.println(k +" : "+v));
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
