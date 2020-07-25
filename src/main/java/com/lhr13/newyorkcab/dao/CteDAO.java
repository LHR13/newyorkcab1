package com.lhr13.newyorkcab.dao;

import com.lhr13.newyorkcab.pojo.Cte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CteDAO extends JpaRepository<Cte, Long> {
}
