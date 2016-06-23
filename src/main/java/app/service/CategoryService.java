package app.service;

import app.dto.CategoryAddForm;
import app.entity.Category;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by PetyoPetrov on 27.05.2016 г..
 */

public interface CategoryService {

    List<Category> getAll();
    Category getById(Long id);
    void addCategory(CategoryAddForm categoryAddForm);
}