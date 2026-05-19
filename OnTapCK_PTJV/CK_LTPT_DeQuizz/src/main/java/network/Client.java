package network;

import dto.QuestionDTO;
import entity.Level;
import entity.Question;

import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try(
                Socket socket = new Socket("21AK22-COM",4741);
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                Scanner sc = new Scanner(System.in);
                ){
            System.out.println("Client khởi động");
            while(true){
                System.out.println("0. thoát");
                System.out.println("1. test câu a");
                System.out.println("2. test câu b");
                System.out.println("3. test câu c");
                System.out.println("Vui lòng chọn : ");
                int choie = sc.nextInt();
                sc.nextLine();

                switch (choie){
                    case 1 -> {
                        out.writeUTF("cauA");
                        out.flush();
                        List<QuestionDTO> list = (List<QuestionDTO>) in.readObject();
                        list.forEach(System.out::println);
                    }
                    case 2 -> {
                        out.writeUTF("cauB");
                        out.flush();
                        Map<Level,Long> map = (Map<Level,Long>) in.readObject();
                        map.forEach((k,v) -> System.out.println(k+" : "+v));
                    }
                    case 3 -> {
                        out.writeUTF("cauC");
                        out.flush();
                        QuestionDTO question = (QuestionDTO) in.readObject();
                        System.out.println(question);
                    }
                    case 0 -> {
                        socket.close();
                        System.out.println("CLient đã thoát");
                        return;
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
