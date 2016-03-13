package com.mawujun.repository.config;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.TypeHandler;
import org.hibernate.SessionFactory;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.filter.logging.Slf4jLogFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.mawujun.repository.DialectEnum;
import com.mawujun.repository.MySqlSessionFactoryBean;
import com.mawujun.repository.mybatis.DatabaseIdProviderCustom;
import com.mawujun.repository.mybatis.PageInterceptor;
import com.mawujun.repository.mybatis.YesNoTypeHandler;


@Configuration
@EnableTransactionManagement(proxyTargetClass=true)
@ComponentScan("com.mawujun")
public class RepositoryConfig {
	private String jdbc_packagesToScan="com.mawujun";
	private String jdbc_db_type="h2";
	
	private String jdbc_driver="org.h2.Driver";
	private String jdbc_url="jdbc:h2:mem:BaseProject;DB_CLOSE_DELAY=-1;MVCC=TRUE";
	private String jdbc_username="sa";
	private String jdbc_password;
	
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		MySqlSessionFactoryBean sqlSessionFactoryBean=new MySqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource());
		sqlSessionFactoryBean.setSessionFactory(sessionFactory());
		
		sqlSessionFactoryBean.setTypeHandlers(new TypeHandler[]{new YesNoTypeHandler()});
		
		PageInterceptor pageInterceptor=new PageInterceptor();
		pageInterceptor.setDialectClass(DialectEnum.valueOf(jdbc_db_type).get_mybatis_dialect());
		sqlSessionFactoryBean.setPlugins(new Interceptor[]{pageInterceptor});
		
		DatabaseIdProviderCustom provider=new DatabaseIdProviderCustom();
		provider.setDatabaseId(jdbc_db_type);
		sqlSessionFactoryBean.setDatabaseIdProvider(provider);
		
		//sqlSessionFactoryBean.setMapperLocations(mapperLocations);
		
		return sqlSessionFactoryBean.getObject();
	}
	
	@Bean
	public DataSource dataSource() throws SQLException {
		//https://github.com/alibaba/druid/wiki/%E9%85%8D%E7%BD%AE_DruidDataSource%E5%8F%82%E8%80%83%E9%85%8D%E7%BD%AE
		DruidDataSource datasource=new DruidDataSource();
		datasource.setDriverClassName(jdbc_driver);
		datasource.setUrl(jdbc_url);
		datasource.setUsername(jdbc_username);
		datasource.setPassword(jdbc_password);
		
		datasource.setDefaultAutoCommit(false);
		
		//通常来说，只需要修改initialSize、minIdle、maxActive。
		datasource.setInitialSize(5);
		datasource.setMaxActive(50);
		datasource.setMinIdle(5);
		//如果用Oracle，则把poolPreparedStatements配置为true，mysql可以配置为false。分库分表较多的数据库，建议配置为false。
		datasource.setPoolPreparedStatements(false);
		
		
		//datasource.setFilters("stat,slf4j");
//		Log4jFilter log_filter=new Log4jFilter();
//		log_filter.setResultSetLogEnabled(false);
		Slf4jLogFilter log_filter=new Slf4jLogFilter();
		log_filter.setResultSetLogEnabled(true);
		ArrayList<Filter> filter_list=new ArrayList<Filter>();
		filter_list.add(log_filter);
		datasource.setProxyFilters(filter_list);
		return datasource;
	}
	
	@Bean
	public MapperScannerConfigurer mapperScannerConfigurer() {
		MapperScannerConfigurer mapperScannerConfigurer=new MapperScannerConfigurer();
		mapperScannerConfigurer.setBasePackage(jdbc_packagesToScan);
		mapperScannerConfigurer.setAnnotationClass(org.springframework.stereotype.Repository.class);
		mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
		return mapperScannerConfigurer;
	}
	
	@Bean
	@Lazy(false)
	public SessionFactory sessionFactory() throws IOException, SQLException {
		LocalSessionFactoryBean localSessionFactoryBean=new LocalSessionFactoryBean();
		localSessionFactoryBean.setDataSource(dataSource());
		localSessionFactoryBean.setPackagesToScan(jdbc_packagesToScan);
		
		Properties hibernateProperties=new Properties();
		hibernateProperties.put("hibernate.hbm2ddl.auto", "update");
		hibernateProperties.put("hibernate.dialect", DialectEnum.valueOf(jdbc_db_type).get_hibernate_dialect());
		
		hibernateProperties.put("hibernate.show_sql", false);
		hibernateProperties.put("hibernate.format_sql", false);
		hibernateProperties.put("use_sql_comments", false);
		hibernateProperties.put("hibernate.max_fetch_depth", 2);
		hibernateProperties.put("hibernate.jdbc.batch_size", 30);
		
		hibernateProperties.put("hibernate.generate_statistics", false);
		hibernateProperties.put("hibernate.cache.use_structured_entries", false);
		localSessionFactoryBean.setHibernateProperties(hibernateProperties);
		localSessionFactoryBean.afterPropertiesSet();
		return (SessionFactory)localSessionFactoryBean.getObject();
	}
	
	
	@Bean
	public PlatformTransactionManager transactionManager() throws IOException, SQLException {
		HibernateTransactionManager transactionManager=new HibernateTransactionManager();
		//transactionManager.setDataSource(dataSource());
		transactionManager.setSessionFactory(sessionFactory());
		transactionManager.setRollbackOnCommitFailure(true);
		
		return transactionManager;
	}

}
