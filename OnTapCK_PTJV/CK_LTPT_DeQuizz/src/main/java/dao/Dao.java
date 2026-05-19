package dao;

import db.JPAUtil;
import entity.Category;
import entity.Level;
import entity.Question;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Dao {
    public List<Question> listQuestionByLevelAndCategory(String categoryName, Level questionLevel){
        try(EntityManager em = JPAUtil.getEntityManager()){
            String jpql = """
                    select q 
                    from Question q
                    join q.category c 
                    where c.name = :cateName and q.questionLevel = :quesLevel
                    """;
            TypedQuery<Question> query = em.createQuery(jpql,Question.class);
            query.setParameter("cateName",categoryName);
            query.setParameter("quesLevel",questionLevel);
            return query.getResultList();
        }
    }

    public Map<Level,Long> countQuestionsByLevelInQuiz(String quizId){
        try(EntityManager em = JPAUtil.getEntityManager()){
            String jpql = """
                 select q.questionLevel,count(q.questionLevel)
                 from Question q
                 join q.quizzes qu
                 where qu.id = :quizId
                 group by q.questionLevel 
                 order by count(q.questionLevel) desc
                 """;
            TypedQuery<Object[]> query = em.createQuery(jpql,Object[].class);
            query.setParameter("quizId",quizId);
            return query.getResultList()
                    .stream()
                    .collect(Collectors.toMap(
                            obj -> (Level) obj[0],
                            obj -> (Long) obj[1],
                            (v1,v2) -> v1,
                            LinkedHashMap::new
                    ));
        }
    }

    public Question addQuestionToCategory(Question question,String categoryId){
        try(EntityManager em = JPAUtil.getEntityManager()){
            em.getTransaction().begin();

            Category category = em.find(Category.class,categoryId);
            if(category == null) {
                return null;
            }
            Question ques = em.find(Question.class,question.getId());
            if(ques != null) return null;

            question.setCategory(category);
            em.persist(question);

            em.getTransaction().commit();
            return question;
        }
    }
}
