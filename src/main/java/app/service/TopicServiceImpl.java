package app.service;

import app.dao.*;
import app.dto.TopicAddForm;
import app.entity.Category;
import app.entity.Topic;
import app.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

/**
 * Created by PetyoPetrov on 29.05.2016 г..
 */
@Service
@Transactional
public class TopicServiceImpl implements TopicService {

    @Autowired
    private TopicDao topicDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private CategoryDao categoryDao;


    @Override
    public Topic getById(Long id) {
        return this.topicDao.getByID(id);
    }


    @Override
    public void create(TopicAddForm topicAddForm) throws Exception {

        Long userId = topicAddForm.getAuthorId();
        User user = userDao.getUserById(userId);
        if (user == null) {
            throw new Exception("Cannot add topic to nonexisting user");

        }
        Long categoryId = topicAddForm.getCategoryId();
        Category cat = categoryDao.getById(categoryId);
        if (cat == null) {
            throw new Exception("Cannot add to nonexisting cat");

        }
        Topic top = new Topic();
        top.setTitle(topicAddForm.getTitle());
        top.setBody(topicAddForm.getBody());
        top.setAuthor(user);
        top.setCategory(cat);
        top.setCreatedOn(new Date());

        topicDao.create(top);
    }

    @Override
    public void delete(Topic topic) {

        this.topicDao.delete(topic);
    }

    @Override
    public void update(Topic topic) {
        this.topicDao.update(topic);
    }
}
