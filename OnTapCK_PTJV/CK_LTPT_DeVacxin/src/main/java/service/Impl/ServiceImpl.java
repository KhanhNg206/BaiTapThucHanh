package service.Impl;

import dao.Dao;
import dto.PersonDTO;
import dto.RecordDTO;
import entity.DoseStatus;
import entity.Person;
import entity.Record;
import entity.Vaccine;
import mapper.PersonMapper;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ServiceImpl implements service.Service {
    private Dao dao;

    public ServiceImpl() {
        this.dao = new Dao();
    }

    @Override
    public boolean createNewRecord(RecordDTO recordDTO){
        return dao.createNewRecord(PersonMapper.recordDTOToEntity(recordDTO));
    }

    @Override
    public boolean updateScheduledRecord(Long recordId, DoseStatus doseStatus){
        return dao.updateScheduledRecord(recordId,doseStatus);
    }

    @Override
    public List<PersonDTO> listObesePeople(){
        return dao.listObesePeople()
                .stream()
                .map(PersonMapper::persontoDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Map<Vaccine,Integer> countRecordsByVaccines(){
        return dao.countRecordsByVaccines();
    }
}
