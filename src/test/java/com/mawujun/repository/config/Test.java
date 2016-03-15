package com.mawujun.repository.config;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.datasource.DataSourceUtils;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext context= new AnnotationConfigApplicationContext(RepositoryConfig.class);
		transaction(context);
		//aaaa(context);
	}
	
	public static void transaction(ApplicationContext context) {  
		EntityTestService entityTestService=context.getBean(EntityTestService.class);
		
		entityTestService.transaction("", "");


		//assertEquals(0,entityTestService.queryAll().size());
		
//		EntityTest entity=new EntityTest();
//		entity.setFirstName("ma");
//		entity.setLastName("wujun");
//		entity.setEmail("160649888@163.com");
//		entityTestService.create(entity);
//		
//		//Integer id=entity.fw
//		
//		if(true){
//			//throw new RuntimeException("");
//		}
//		
//		EntityTest entity1=new EntityTest();
//		entity1.setFirstName("ma1");
//		entity1.setLastName("wujun1");
//		entity1.setEmail("160649888@163.com1");
//		entityTestService.create(entity1);
//		
//		//entityTestService.queryAll().size();
//		assertEquals(2,entityTestService.queryAll().size());
		
//		try {
//			entityTestService.transaction("d075282b-cb98-42b5-ab7c-991e9cfdba1b","11396287-d05d-4b43-b54f-3d83c3f137af");
//		} catch(Exception e){
//			e.printStackTrace();
//		}
//		
//		assertEquals(2,entityTestService.queryAll().size());
    }  
	
	public static void aaaa(ApplicationContext context) { 
		EntityTestService entityTestService=context.getBean(EntityTestService.class);
		entityTestService.queryAll().size();
		assertEquals(2,entityTestService.queryAll().size());
	}

}