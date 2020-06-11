package com.lhr13.newyorkcab.dao;

import com.lhr13.newyorkcab.pojo.Gpt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GptDAO extends JpaRepository<Gpt, Long> {
}
