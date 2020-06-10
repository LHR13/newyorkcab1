package com.lhr13.newyorkcab.dao;

import com.lhr13.newyorkcab.pojo.Wbd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WbdDAO extends JpaRepository<Wbd, Long> {
}
