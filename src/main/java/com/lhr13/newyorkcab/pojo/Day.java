package com.lhr13.newyorkcab.pojo;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;

@Component
@Entity(name = "day")
public class Day implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String day;
    @Column
    private Long count;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Day() {
    }

    @Override
    public String toString() {
        return "Day{" +
                "id=" + id +
                ", day='" + day + '\'' +
                ", count=" + count +
                '}';
    }
}
