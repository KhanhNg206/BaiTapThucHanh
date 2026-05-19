package network;

import entity.Level;
import entity.Question;
import entity.Type;
import service.Impl.ServiceImpl;
import service.Service;

import java.io.DataInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientHandle implements Runnable{
    private Socket socket;
    private Service service;

    public ClientHandle(Socket socket) {
        this.socket = socket;
        this.service = new ServiceImpl();
    }

    @Override
    public void run() {
        try(
                DataInputStream in = new DataInputStream(socket.getInputStream());
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                ){
            while (true){
                String command = in.readUTF();
                switch (command){
                    case "cauA" -> {
                        out.writeObject(service.listQuestionByLevelAndCategory("History", Level.EASY));
                        out.flush();
                    }
                    case "cauB" -> {
                        out.writeObject(service.countQuestionsByLevelInQuiz("QZ108"));
                        out.flush();
                    }
                    case "cauC" -> {
                        Question question = new Question();
                        question.setId("ngocoanh");
                        question.setQuestionLevel(Level.EASY);
                        question.setType(Type.ESSAY);
                        question.setQuestionText("new text");
                        out.writeObject(service.addQuestionToCategory(question,"C101"));
                        out.flush();
                    }
                }

            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
