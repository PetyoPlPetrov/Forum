package app.autentication;

import app.entity.Authority;
import app.entity.Role;
import app.entity.User;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.stream.Collectors;


/**
 * Created by PetyoPetrov on 22.06.2016 г..
 */
public class CurrentUser extends org.springframework.security.core.userdetails.User {

    private User user;

    public CurrentUser(User user) {
        super(user.getUsername(), String.valueOf(user.getPassword()),
                AuthorityUtils.createAuthorityList(
                      user.getRoles().
                                stream().
                                map(Authority::getName).
                               toArray(String[]::new)));
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public Long getId() {
        return Long.valueOf(user.getId());
    }

    public Role[] getRole() {
        return user.getRoles().stream().map(r->Role.valueOf(r.getName())).toArray(r->new Role[r]);
    }

}