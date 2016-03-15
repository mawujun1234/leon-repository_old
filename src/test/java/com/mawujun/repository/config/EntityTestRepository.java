package com.mawujun.repository.config;

import org.springframework.stereotype.Repository;

import com.mawujun.repository.IRepository;

@Repository
//@Transactional
public interface EntityTestRepository extends IRepository<EntityTest, String> {

}
