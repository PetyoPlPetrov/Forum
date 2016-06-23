package app.dao;

import app.entity.Authority;
import app.entity.User;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by PetyoPetrov on 24.05.2016 г..
 */
public interface UserDao {
    List<User> getAll();
    User getUserByUsernameAndPas(String name,String password);

    User getUserById(Long id);

    void create(User user, Authority authority);
    User getUserByUserName(String userName);

   // void update(User user);

   // void delete(User user);
   // boolean isValidUser(String username,int password) throws SQLException;
}
