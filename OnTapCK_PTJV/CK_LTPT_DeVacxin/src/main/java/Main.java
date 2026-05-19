import dao.Dao;
import entity.DoseStatus;
import entity.Record;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
//        EntityManager em = Persistence.createEntityManagerFactory("mariadb")
//                .createEntityManager();

        Dao dao = new Dao();
//        Record record = new Record();
//        record.setId(21L);
//        record.setStatus(DoseStatus.CANCELLED);
//        record.setDoseNumber(1111111);
//        record.setInjectionDate(LocalDate.now());
//
//        System.out.println(dao.createNewRecord(record));

        //b

//        System.out.println(dao.updateScheduledRecord(16L,DoseStatus.COMPLETED));

//        dao.listObesePeople().forEach(System.out::println);

        dao.countRecordsByVaccines().forEach((k,v) -> System.out.println(k.getName() +" : "+v));
    }
}
