package app.entity;

import javax.persistence.*;

/**
 * Created by PetyoPetrov on 22.06.2016 г..
 */
@Entity
@Table(name = "authorities")
public class Authority {

    private Long id;
    private String name;
    private User user;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public  void setName(String name) {
        this.name = name;
    }
    @ManyToOne
    @JoinColumn(name = "user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
