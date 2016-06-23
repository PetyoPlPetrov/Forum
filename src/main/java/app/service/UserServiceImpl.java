package app.service;

import app.dao.UserDao;
import app.dto.UserAddForm;
import app.entity.Authority;
import app.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by PetyoPetrov on 24.05.2016 г..
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;


    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public User getUserById(Long id) {

        return userDao.getUserById(id);
    }

    @Override
    public User getUserByUserName(String userName) {
        return userDao.getUserByUserName(userName);
    }

    @Override
    public User getUserBYIdANdPassword(String name, String password) {
        return userDao.getUserByUsernameAndPas(name,password);
    }

    @Override
    public void create(UserAddForm userAddForm) {
         BCryptPasswordEncoder encoder= new BCryptPasswordEncoder();

        User user = new User();
        user.setUsername(userAddForm.getUsername());
        user.setPassword(encoder.encode(((userAddForm.getPassword()))));
        Authority authority=new Authority();
        authority.setName(userAddForm.getRole().toString());
        authority.setUser(user);
        user.getRoles().add(authority);
        this.userDao.create(user,authority);
    }



}
