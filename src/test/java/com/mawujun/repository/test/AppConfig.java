package com.mawujun.repository.test;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;

//@Configuration
@ComponentScan(basePackages="com.mawujun.repository.test",
includeFilters = @Filter(type = FilterType.ANNOTATION, value = {Controller.class}))
@EnableAspectJAutoProxy(proxyTargetClass=true)
@Import({ RepositoryConfig.class})
public class AppConfig {

}
