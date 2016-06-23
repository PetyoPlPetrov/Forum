package app.service;

import app.dto.UserAddForm;
import app.entity.Authority;
import app.entity.Category;
import app.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by PetyoPetrov on 24.05.2016 г..
 */
public interface UserService {
    List<User> getAll();

    User getUserById(Long id);
    User getUserByUserName(String userName);
    User getUserBYIdANdPassword(String name,String password);

    void create(UserAddForm userAddForm);

//    void update(int userID, String username, int password) throws Exception;
//
//    void delete(int id) throws Exception;
//    boolean isValidUser(String username,int password) throws SQLException;



}
