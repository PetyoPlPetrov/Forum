package app.dao;

import app.entity.Topic;

/**
 * Created by PetyoPetrov on 29.05.2016 г..
 */
public interface TopicDao {

    void create(Topic topic);
    Topic getByID(Long id);
    void delete(Topic topic);
    void update(Topic topic);

}
