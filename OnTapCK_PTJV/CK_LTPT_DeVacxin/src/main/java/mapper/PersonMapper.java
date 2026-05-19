package mapper;

import dto.PersonDTO;
import dto.RecordDTO;
import entity.DoseStatus;
import entity.Person;
import entity.Record;

import java.time.LocalDate;

public class PersonMapper {
    public static PersonDTO persontoDTO(Person person){
        PersonDTO personDTO = new PersonDTO();
        personDTO.setId(person.getId());
        personDTO.setDob(person.getDob());
        personDTO.setHeight(person.getHeight());
        personDTO.setWeight(person.getWeight());
        personDTO.setFullName(person.getFullName());
        return personDTO;
    }

    public static Record recordDTOToEntity(RecordDTO recordDTO){
        Record record = new Record();
        record.setId(recordDTO.getId());
        record.setInjectionDate(recordDTO.getInjectionDate());
        record.setDoseNumber(recordDTO.getDoseNumber());
        record.setStatus(recordDTO.getStatus());
        return record;
    }
}
