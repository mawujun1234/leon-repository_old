package com.mawujun.repository.config;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mawujun.repository.EntityTest;
import com.mawujun.repository.IRepository;
import com.mawujun.repository.hibernate.HibernateDao;
import com.mawujun.service.AbstractService;

@Service
@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class EntityTestService  extends AbstractService<EntityTest,String>{
	@Autowired
	EntityTestRepository entityTestRepository;
	@Autowired
	DataSource dataSource;
	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public IRepository<EntityTest, String> getRepository() {
		// TODO Auto-generated method stub
		return entityTestRepository;
	}
	public void transaction(String id1,String id2){
		
		
		EntityTest entity=new EntityTest();
		entity.setFirstName("ma");
		entity.setLastName("wujun");
		entity.setEmail("160649888@163.com");
		//entityTestRepository.create(entity);
		sessionFactory.getCurrentSession().save(entity);
		

//		Connection conn = DataSourceUtils.getConnection(dataSource);
//		try {
//			conn.commit();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		if(true){
//			throw new RuntimeException("");
//		}
//		
//		EntityTest entity1=new EntityTest();
//		entity1.setFirstName("ma1");
//		entity1.setLastName("wujun1");
//		entity1.setEmail("160649888@163.com1");
//		entityTestRepository.create(entity1);
		
		
//		EntityTest entity1=new EntityTest();
//		//entity1.setVersion(id1);
//		entity1.setId(id1);
//		EntityTest entity2=new EntityTest();
//		entity2.setId(id2);
//		//entity2.setVersion(1);
//		
//		entityTestRepository.delete(entity1);
//		
//		if(true){
//			throw new RuntimeException("");
//		}
//		entityTestRepository.delete(entity2);
	}

}
