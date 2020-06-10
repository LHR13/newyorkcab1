package com.lhr13.newyorkcab.dao;

import com.lhr13.newyorkcab.pojo.LorS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LorSDAO extends JpaRepository<LorS, Long> {

}
