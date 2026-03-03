package iuh.fit.infrastructure.persistence;

import iuh.fit.core.entity.Student;
import iuh.fit.core.repository.StudentRepository;
import iuh.fit.infrastructure.db.Neo4jConnManager;
import iuh.fit.infrastructure.mapper.GenericDataMapper;
import iuh.fit.infrastructure.mapper.JacksonDataMapper;

import org.neo4j.driver.Session;
import org.neo4j.driver.Result;
import org.neo4j.driver.Record;
import org.neo4j.driver.types.Node;

import java.util.*;

public class StudentRepositoryImpl implements StudentRepository {

    private final Neo4jConnManager connManager;
    private final JacksonDataMapper<Student> mapper = new JacksonDataMapper<>();

    public StudentRepositoryImpl(Neo4jConnManager connManager) {
        this.connManager = connManager;
    }

    @Override
    public Student save(Student student) {
        try (Session session = connManager.getSession()) {

            Map<String, Object> params = mapper.toMap(student);

            String cypher = """
                    MERGE (s:Student {student_id: $student_id})
                    SET s.name = $name,
                        s.gpa = $gpa
                    """;

            session.run(cypher, params);
        }
        return student;
    }

    @Override
    public Optional<Student> findById(String id) {
        try (Session session = connManager.getSession()) {

            String cypher = """
                    MATCH (s:Student {student_id: $id})
                    RETURN s
                    """;

            Result result = session.run(cypher, Map.of("id", id));

            if (result.hasNext()) {
                Record record = result.next();
                Node node = record.get("s").asNode();

                return Optional.of(
                        mapper.fromMap(node.asMap(), Student.class)
                );
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Student> findAll() {
        List<Student> list = new ArrayList<>();

        try (Session session = connManager.getSession()) {

            String cypher = "MATCH (s:Student) RETURN s";

            Result result = session.run(cypher);

            while (result.hasNext()) {
                Record record = result.next();
                Node node = record.get("s").asNode();

                list.add(
                        mapper.fromMap(node.asMap(), Student.class)
                );
            }
        }
        return list;
    }

    @Override
    public void deleteById(String id) {
        try (Session session = connManager.getSession()) {

            String cypher = """
                    MATCH (s:Student {student_id: $id})
                    DELETE s
                    """;

            session.run(cypher, Map.of("id", id));
        }
    }

    public static void main(String[] args) {

        String uri = "bolt://localhost:7687";
        String username = "neo4j";
        String password = "123456khanh";
        String dbName = "khanh123";

        Neo4jConnManager conn = new Neo4jConnManager(uri, username, password, dbName);

        StudentRepository studentRepository = new StudentRepositoryImpl(conn);

        Student student = studentRepository.findById("22").orElse(null);
        System.out.println(student);

        Student st = Student.builder()
                .id("23634741")
                .name("Nhat Khanh")
                .gpa(3.6)
                .build();

        studentRepository.save(st);

        System.out.println("DONE");
    }}