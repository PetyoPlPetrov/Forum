package app.service.userdetails;

import app.autentication.CurrentUser;
import app.entity.User;
import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by PetyoPetrov on 22.06.2016 г..
 */


    @Service
    @Transactional
    public class CurrentUserDetailsService implements UserDetailsService {
        @Autowired
        private UserService userService;


        @Override
        public CurrentUser loadUserByUsername(String username) throws UsernameNotFoundException {
            User user = userService.getUserByUserName(username);
            return new CurrentUser(user);
        }
    }

