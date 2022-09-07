package ru.job4j.cars.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "auto_post")
public class Post {
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    private String text;
    private Timestamp created;
    private int auto_user_id;
    private int car_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
