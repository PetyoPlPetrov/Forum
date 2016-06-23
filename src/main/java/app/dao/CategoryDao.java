package app.dao;

import app.entity.Category;

import java.util.List;

/**
 * Created by PetyoPetrov on 29.05.2016 г..
 */
public interface CategoryDao {

    List<Category> getAll();
    Category getById(Long id);
    void create(Category category);



}
