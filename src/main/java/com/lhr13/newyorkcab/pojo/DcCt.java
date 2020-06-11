package com.lhr13.newyorkcab.pojo;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "dcct")
public class DcCt implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String distance;
    @Column
    private Long count;

    @Override
    public String toString() {
        return "Distance{" +
                "id=" + id +
                ", distance='" + distance + '\'' +
                ", count=" + count +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public DcCt() {
    }
}
