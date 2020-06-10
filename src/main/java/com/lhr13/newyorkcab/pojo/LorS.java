package com.lhr13.newyorkcab.pojo;

import javax.persistence.*;

@Entity
public class LorS {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String longorshort;
    @Column Long count;

    @Override
    public String toString() {
        return "lors{" +
                "id=" + id +
                ", longorshort='" + longorshort + '\'' +
                ", count=" + count +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLongorshort() {
        return longorshort;
    }

    public void setLongorshort(String longorshort) {
        this.longorshort = longorshort;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public LorS() {

    }
}
