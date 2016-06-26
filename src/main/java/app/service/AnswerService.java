package app.service;

import app.dto.AnswerAddForm;
import app.entity.Answer;

import java.util.List;

/**
 * Created by PetyoPetrov on 31.05.2016 г..
 */
public interface AnswerService {

    void create(AnswerAddForm form);
    List<Answer> getAnswers(Long id, int page);
    Long getCount(Long topicId);
}
