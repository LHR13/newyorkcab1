package com.lhr13.newyorkcab.dao;

import com.lhr13.newyorkcab.pojo.Mct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MctDAO extends JpaRepository<Mct, Long> {
}
