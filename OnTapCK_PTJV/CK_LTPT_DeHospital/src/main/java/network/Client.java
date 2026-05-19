package network;

import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try (
                Socket socket = new Socket("21AK22-COM",4741);
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                Scanner sc = new Scanner(System.in);
                ){
            System.out.println("Client đang chạy");
            while (true){
                System.out.println("Vui lòng chọn : ");
                System.out.println("0. thoát");
                System.out.println("0. test câu a");
                System.out.println("0. test câu b");
                System.out.println("0. test câu c");
                int choice = sc.nextInt();
                sc.nextLine();
                switch (choice){
                    case 0 -> {
                        socket.close();
                        System.out.println("Client đã thoát");
                        return;
                    }
                    case 1 -> {
                        out.writeUTF("cauA");
                        System.out.println("Nhập mã doctor : ");
                        String doctorId = sc.nextLine();
                        System.out.println("Nhập mã patient : ");
                        String patientId = sc.nextLine();
                        out.writeUTF(doctorId);
                        out.writeUTF(patientId);
                        out.flush();
                        Boolean result = (Boolean) in.readObject();
                        System.out.println(result);
                    }
                    case 2 -> {
                        out.writeUTF("cauB");
                        out.flush();
                        List<Object[]> list = (List<Object[]>) in.readObject();
                        for (Object[] obj : list) {
                            System.out.println(Arrays.toString(obj));
                        }
                    }
                    case 3 -> {
                        out.writeUTF("cauC");
                        out.flush();
                        List<Object[]> list = (List<Object[]>) in.readObject();
                        for (Object[] obj : list) {
                            System.out.println(Arrays.toString(obj));
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
