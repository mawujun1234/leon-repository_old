package com.mawujun.repository.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mawujun.repository.EntityTest;
import com.mawujun.repository.IRepository;
import com.mawujun.service.AbstractService;

@Service
@Transactional
public class EntityTestService  extends AbstractService<EntityTest,Integer>{
	@Autowired
	EntityTestRepository entityTestRepository;
	
	@Override
	public IRepository<EntityTest, Integer> getRepository() {
		// TODO Auto-generated method stub
		return entityTestRepository;
	}

}
