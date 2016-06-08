package com.kingdee.ebank.util;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Spring 单元测试基类
 * @author ThinkGem
 * @version 2013-05-15
 */
@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = { "classpath:META-INF/spring/dubbo-ebank-provider.xml", "classpath:mybatis-config.xml" }) 
public class SpringTransactionalContextTests{

}
