package ru.job4j.cars.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "auto_post")
public class Post {
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    private String text;
    private Timestamp created;
    private byte[] photo;
    @ManyToOne()
    @JoinColumn(name = "auto_user_id")
    private Driver driver;
    @OneToOne()
    @JoinColumn(name = "car_id")
    private Car car;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
