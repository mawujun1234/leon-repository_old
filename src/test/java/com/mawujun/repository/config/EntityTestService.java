package com.mawujun.repository.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mawujun.repository.EntityTest;
import com.mawujun.repository.IRepository;
import com.mawujun.service.AbstractService;

@Service
@Transactional(rollbackFor=Exception.class)
public class EntityTestService  extends AbstractService<EntityTest,Integer>{
	@Autowired
	EntityTestRepository entityTestRepository;
	
	@Override
	public IRepository<EntityTest, Integer> getRepository() {
		// TODO Auto-generated method stub
		return entityTestRepository;
	}
	
	public void transaction(Integer id1,Integer id2){
		EntityTest entity1=new EntityTest();
		//entity1.setVersion(id1);
		entity1.setId(1);
		EntityTest entity2=new EntityTest();
		entity2.setId(id2);
		//entity2.setVersion(1);
		
		entityTestRepository.delete(entity1);
		
		if(true){
			//throw new RuntimeException("");
		}
		entityTestRepository.delete(entity2);
	}

}
