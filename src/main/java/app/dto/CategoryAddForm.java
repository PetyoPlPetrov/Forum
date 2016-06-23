package app.dto;

import java.util.Date;

/**
 * Created by PetyoPetrov on 29.05.2016 г..
 */
public class CategoryAddForm {

    private Long id;
    private String name;
    private Date CreatedOn;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatedOn() {
        return CreatedOn;
    }

    public void setCreatedOn(Date createdOn) {
        CreatedOn = createdOn;
    }
}
