package iuh.fit.core.repository;

import iuh.fit.core.entity.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository {

    Student save(Student student);

    Optional<Student> findById(String id);

    List<Student> findAll();

    void deleteById(String id);
}