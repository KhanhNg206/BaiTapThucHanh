import dao.Dao;
import entity.Level;
import entity.Question;
import entity.Type;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import service.Impl.ServiceImpl;
import service.Service;

public class Main {
    public static void main(String[] args) {
        Dao dao = new Dao();
        Service service = new ServiceImpl();
//        dao.listQuestionByLevelAndCategory("History", Level.EASY).forEach(System.out::println);

//        dao.countQuestionsByLevelInQuiz("QZ108").forEach((k,v) -> System.out.println(k + " : "+v));

        Question question = new Question();
        question.setId("oanh");
        question.setQuestionLevel(Level.EASY);
        question.setType(Type.ESSAY);
        question.setQuestionText("new text");
        System.out.println(service.addQuestionToCategory(question,"C101"));
    }
}
