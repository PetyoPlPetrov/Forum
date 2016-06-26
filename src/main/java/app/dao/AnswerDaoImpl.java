package app.dao;

import app.entity.Answer;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by PetyoPetrov on 31.05.2016 г..
 */
@Repository
public class AnswerDaoImpl implements AnswerDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Answer answer) {
        this.em.persist(answer);
        this.em.flush();
    }

    @Override
    public List<Answer> getAnswers(Long id,int page) {
        Query query=this.em.createQuery("SELECT a FROM Answer a WHERE a.topic.id= :topicId ORDER BY a.id")
                .setParameter("topicId",Integer.parseInt(String.valueOf(id)))
                .setFirstResult((page-1)*2)
                .setMaxResults(2);
        List<Answer> answers=query.getResultList();
        if(answers.isEmpty()){
            return null;
        }
        return answers;


    }

    @Override
    public Long getCount(Long topicId) {
        Query query=this.em.createQuery("SELECT count(a) FROM Answer a WHERE a.topic.id= :topicId")
                .setParameter("topicId",Integer.parseInt(String.valueOf(topicId)));
        List<Long> result=query.getResultList();
        if(result.isEmpty()){
            return 0L;
        }
        return result.get(0);
    }
}
