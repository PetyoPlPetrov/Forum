package app.dao;

import app.dto.CategoryAddForm;
import app.entity.Category;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by PetyoPetrov on 29.05.2016 г..
 */
@Repository
public class CategoryDaoiImpl implements CategoryDao{
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Category> getAll() {
        return em.createQuery("SELECT u FROM Category u").getResultList();
    }

    @Override
    public Category getById(Long id) {
        Query query=em.createQuery("SELECT c FROM Category c WHERE c.id=:id").setParameter("id",id);
        List<Category> categories=query.getResultList();
        return categories.get(0);
    }

    @Override
    public void create(Category category) {
        em.persist(category);
        em.flush();
    }

}
