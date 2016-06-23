package app.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by PetyoPetrov on 25.05.2016 г..
 */
@Entity
@Table(name = "categories")
public class Category {
//

    private Long id;


    private String name;
    private Set<Topic> topics = new HashSet<>(0);
    private Date createdOn;


    public String getName() {
        return name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
    public Set<Topic> getTopics() {
        return topics;
    }

    public void setTopics(Set<Topic> topics) {
        this.topics = topics;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date date) {
        this.createdOn = date;
    }

    public Category() {
    }

    public void setName(String subject) {
        this.name = subject;
    }

    public Category(String subject) {
        this.name = subject;

    }


}
