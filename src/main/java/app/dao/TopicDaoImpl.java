package app.dao;

import app.entity.Topic;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Queue;

/**
 * Created by PetyoPetrov on 29.05.2016 г..
 */
@Repository
public class TopicDaoImpl implements TopicDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Topic topic) {
        em.persist(topic);
        em.flush();

    }

    @Override
    public Topic getByID(Long id) {
        Query query=em.createQuery("SELECT t FROM Topic t WHERE t.id=:id").setParameter("id",Integer.valueOf
                (String.valueOf(id)));
         List<Topic> topics=query.getResultList();
        if(topics.isEmpty()){
            return null;
        }

        return topics.get(0);
    }
}
