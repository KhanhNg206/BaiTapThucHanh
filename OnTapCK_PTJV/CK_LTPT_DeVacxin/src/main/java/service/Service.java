package service;

import dto.PersonDTO;
import dto.RecordDTO;
import entity.DoseStatus;
import entity.Vaccine;

import java.util.List;
import java.util.Map;

public interface Service {
    boolean createNewRecord(RecordDTO recordDTO);

    boolean updateScheduledRecord(Long recordId, DoseStatus doseStatus);

    List<PersonDTO> listObesePeople();

    Map<Vaccine, Integer> countRecordsByVaccines();
}
