package app.dao;

import app.entity.Answer;

import java.util.List;

/**
 * Created by PetyoPetrov on 31.05.2016 г..
 */
public interface AnswerDao {


    void create(Answer answer);
    List<Answer> getAnswers(Long id,int page);
    Long getCount(Long topicId);
}
