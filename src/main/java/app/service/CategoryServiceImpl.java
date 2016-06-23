package app.service;

import app.dao.CategoryDao;
import app.dao.CategoryDaoiImpl;
import app.dto.CategoryAddForm;
import app.entity.Category;
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
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;


    @Override
    public List<Category> getAll() {
        return categoryDao.getAll();
    }

    @Override
    public Category getById(Long id) {
        return categoryDao.getById(id);
    }

    @Override
    public void addCategory(CategoryAddForm categoryAddForm) {
        Category cat = new Category();
        cat.setName(categoryAddForm.getName());
        cat.setId(categoryAddForm.getId());
        categoryDao.create(cat);
    }
}
