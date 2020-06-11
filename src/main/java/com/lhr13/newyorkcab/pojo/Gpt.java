package com.lhr13.newyorkcab.pojo;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "gpt")
public class Gpt implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String passagernum;
    @Column
    private String time;
    @Column
    private Long count;

    public Gpt() {
    }

    @Override
    public String toString() {
        return "Gpt{" +
                "id=" + id +
                ", passagernum='" + passagernum + '\'' +
                ", time='" + time + '\'' +
                ", count=" + count +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassagernum() {
        return passagernum;
    }

    public void setPassagernum(String passagernum) {
        this.passagernum = passagernum;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
