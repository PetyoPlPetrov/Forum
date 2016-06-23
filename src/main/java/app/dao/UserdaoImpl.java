package app.dao;

import app.entity.Authority;
import app.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


import javax.persistence.Query;
import javax.sql.DataSource;


/**
 * Created by PetyoPetrov on 24.05.2016 г..
 */
@Repository
public class UserdaoImpl implements UserDao {


    @PersistenceContext
    private EntityManager em;

    @Override
    public List<User> getAll() {
        return em.createQuery("SELECT u FROM User u").getResultList();
    }

    @Override
    public User getUserByUsernameAndPas(String name, String password) {
        Query query=em.createQuery("SELECT u FROM User u WHERE u.username=:username AND u.password=:password").
                setParameter("username",name).setParameter("password",password);
        List<User> result=query.getResultList();
        if(result.isEmpty()){
            return null;
        }
        return result.get(0);

    }

    @Override
    public User getUserById(Long idd) {
        Query query= em.createQuery("SELECT u FROM User u WHERE u.id=:idd").setParameter("idd",idd);
        List<User> users=query.getResultList();
        if(users.isEmpty()){
            return null;
        }

        return users.get(0);
    }

    @Override
    public void create(User user, Authority authority) {
        this.em.persist(authority);
        this.em.persist(user);
        this.em.flush();


    }

    @Override
    public User getUserByUserName(String userName) {
        Query query= em.createQuery("SELECT u FROM User u WHERE u.username=:name").setParameter("name",userName);
        List<User> users=query.getResultList();
        if(users.isEmpty()){
            return null;
        }

        return users.get(0);
    }



}


