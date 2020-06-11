package com.lhr13.newyorkcab.dao;

import com.lhr13.newyorkcab.pojo.DcCt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DCDAO extends JpaRepository<DcCt, Long> {
}
