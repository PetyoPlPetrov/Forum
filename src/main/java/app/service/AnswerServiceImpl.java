package app.service;

import app.dao.AnswerDao;
import app.dao.TopicDao;
import app.dao.UserDao;
import app.dto.AnswerAddForm;
import app.entity.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by PetyoPetrov on 31.05.2016 г..
 */
@Service
@Transactional
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    private TopicDao topicDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private AnswerDao answerDao;


    @Override
    public void create(AnswerAddForm form) {
        Answer answer = new Answer();
        answer.setBody(form.getBody());
        answer.setCreatedOn(new Date());
        answer.setTopic(topicDao.getByID(form.getTopicId()));
        answer.setAuthor(userDao.getUserById(form.getUserId()));
        answerDao.create(answer);
    }

    @Override
    public List<Answer> getAnswers(Long id, int page) {
        return this.answerDao.getAnswers(id,page);
    }

    @Override
    public Long getCount(Long topicId) {
        return this.answerDao.getCount(topicId);
    }
}
