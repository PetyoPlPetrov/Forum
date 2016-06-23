package app.dto;

import app.entity.Topic;

import java.util.Date;

/**
 * Created by PetyoPetrov on 29.05.2016 г..
 */
public class AnswerAddForm {

    private Long userId;
    private String body;
    private Long topicId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }
}
