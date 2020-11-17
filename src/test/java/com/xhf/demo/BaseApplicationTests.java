package com.xhf.demo;

import com.xhf.demo.entity.User;
import com.xhf.demo.service.UserService;
import com.xhf.demo.service.impl.UserServiceImpl;
import com.xhf.demo.util.SpringContextUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BaseApplicationTests {

    @Test
    public void contextLoads() {
        //UserServiceImpl userServiceImpl = (UserServiceImpl)SpringUtil.getBean("userServiceImpl");
        //UserServiceImpl userServiceImpl = SpringUtil.getBean(UserServiceImpl.class);
        UserService userServiceImpl = SpringContextUtil.getBean(UserService.class);

        User xxx = userServiceImpl.xxx(1);
        System.out.println(xxx.toString());
    }

    @Test
    public void contextLoads1() {
        Map<String, Object> map = SpringContextUtil.getBeansWithAnnotation(Service.class);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }
    }

    @Test
    public void contextLoads2() {
        ApplicationContext applicationContext = SpringContextUtil.getApplicationContext();
        Service annotation = applicationContext.findAnnotationOnBean("userServiceImpl", Service.class);
    }
}

