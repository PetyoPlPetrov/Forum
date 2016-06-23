package app.service;

import app.dto.TopicAddForm;
import app.entity.Category;
import app.entity.Topic;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by PetyoPetrov on 29.05.2016 г..
 */
@Service
@Transactional
public interface TopicService {

   Topic getById(Long id);

    void create(TopicAddForm topicAddForm) throws Exception;

}
