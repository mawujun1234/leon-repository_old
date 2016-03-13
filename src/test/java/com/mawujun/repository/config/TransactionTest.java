package com.mawujun.repository.config;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.jdbc.Sql;

import com.mawujun.repository.EntityTest;

public class TransactionTest {
	static ApplicationContext context;
	@BeforeClass  
    public static void setUpBeforeClass() throws Exception { 
		 context = new AnnotationConfigApplicationContext(RepositoryConfig.class);
    }  
	
	@Test  
	//@Sql("/test-user-data.sql")
    public void transaction() {  
		EntityTestService entityTestService=context.getBean(EntityTestService.class);

		assertEquals(0,entityTestService.queryAll().size());
		
		EntityTest entity=new EntityTest();
		entity.setFirstName("ma");
		entity.setLastName("wujun");
		entity.setEmail("160649888@163.com");
		entityTestService.create(entity);
		
		//Integer id=entity.fw
		
		EntityTest entity1=new EntityTest();
		entity1.setFirstName("ma1");
		entity1.setLastName("wujun1");
		entity1.setEmail("160649888@163.com1");
		entityTestService.create(entity1);
		
		assertEquals(2,entityTestService.queryAll().size());
		
		try {
			entityTestService.transaction(entity.getId(),entity1.getId());
		} catch(Exception e){
			e.printStackTrace();
		}
		
		assertEquals(2,entityTestService.queryAll().size());
    }  
}
