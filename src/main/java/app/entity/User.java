package app.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by PetyoPetrov on 24.05.2016 г..
 */
@Entity
public class User {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String username;
    private String password;
    private Set<Answer> answers = new HashSet<>(0);
    private Set<Topic> topics = new HashSet<>(0);
    private Set<Authority> roles=new HashSet<>(0);

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "user")
    public  Set<Authority> getRoles() {
        return roles;
    }

    public void setRoles(Set<Authority> roles) {
        this.roles = roles;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "author")
    public Set<Answer> getAnswers() {
        return answers;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "author")
    public Set<Topic> getTopics() {
        return topics;
    }

    public void setTopics(Set<Topic> topics) {
        this.topics = topics;
    }

    public void setAnswers(Set<Answer> answers) {
        this.answers = answers;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
