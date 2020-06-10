package com.lhr13.newyorkcab.dao;

import com.lhr13.newyorkcab.pojo.Day;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DayDAO extends JpaRepository<Day, Long> {
}
