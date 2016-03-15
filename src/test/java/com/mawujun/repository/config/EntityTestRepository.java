package com.mawujun.repository.config;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mawujun.repository.EntityTest;
import com.mawujun.repository.IRepository;

@Repository
//@Transactional
public interface EntityTestRepository extends IRepository<EntityTest, String> {

}
