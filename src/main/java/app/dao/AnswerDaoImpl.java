package app.dao;

import app.entity.Answer;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
}
