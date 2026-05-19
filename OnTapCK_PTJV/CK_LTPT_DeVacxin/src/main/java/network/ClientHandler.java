package network;

import dto.RecordDTO;
import entity.DoseStatus;
import entity.Record;
import service.Impl.ServiceImpl;
import service.Service;

import java.io.DataInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.LocalDate;

public class ClientHandler implements Runnable{
    private Service service;
    private Socket socket;

    public ClientHandler(Socket socket) {
        this.service = new ServiceImpl();
        this.socket = socket;
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
        RecordDTO record = new RecordDTO();
        record.setId(23L);
        record.setStatus(DoseStatus.SCHEDULED);
        record.setDoseNumber(1111111);
        record.setInjectionDate(LocalDate.now());
        out.writeObject(service.createNewRecord(record));
        out.flush();
                    }
                    case  "cauB" -> {
                        out.writeObject(service.updateScheduledRecord(22L,DoseStatus.COMPLETED));
                        out.flush();
                    }
                    case  "cauC" -> {
                        out.writeObject(service.listObesePeople());
                        out.flush();
                    }
                    case  "cauD" -> {
                        out.writeObject(service.countRecordsByVaccines());
                        out.flush();
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
