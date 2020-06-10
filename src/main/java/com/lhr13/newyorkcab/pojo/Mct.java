package com.lhr13.newyorkcab.pojo;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "mct")
public class Mct implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String hour;
    @Column
    private Long count;

    @Override
    public String toString() {
        return "Mct{" +
                "id=" + id +
                ", hour='" + hour + '\'' +
                ", count='" + count + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Mct() {
    }
}
