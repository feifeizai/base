package com.xhf.demo.service;

import com.xhf.demo.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@EnableTransactionManagement
public class UserServiceTest {
    @Autowired
    UserService userService;

    @Test
    //@Transactional
    public void save() {
        User user = new User();
        user.setUsername("胡巴");
        user.setPassword("888");
        user.setNickname("小八");

        userService.save(user);
        int i=1/0;
//        throw new Exception();
    }

    @Test
    public void update() throws Exception {

        Optional<User> optional = userService.findById(8);
        if(optional.isPresent()){
            User user = optional.get();
            user.setCheckcode("1234");
            userService.save(user);
        }
    }

    @Test
    public void deleteById(){
        //会先查找，然后删除
        userService.deleteById(8);
    }

    @Test
    public void delete(){
        User user = new User();
        user.setId(9);
        //user.setUsername("赵六");
        //user.setPassword("000");
        //会先查找，然后删除
        userService.delete(user);
    }

    @Test
    public void findAll1(){
        User user = new User();
        user.setNickname("小八");
        List<User> all = userService.findAll(user);
        System.out.println(all.toString());

        userService.deleteInBatch(all);
    }

    @Test
    public void findAll2(){
        List<Integer> list = new ArrayList<>();
        list.add(19);
        list.add(20);

        List<User> all = userService.findAllById(list);
        System.out.println(all.toString());
    }

    @Test
    public void findAll3(){
        User user = new User();
        user.setNickname("小八");
        List<User> all = userService.findAll(user,new Sort(Sort.Direction.ASC,"id"));
        System.out.println(all.toString());
    }

    @Test
    public void findAll4(){
        Page<User> all = userService.findAll(0, 3);
        System.out.println(all.getContent().toString());
    }

    @Test
    public void findOne(){
        User user = new User();
        user.setNickname("小七");
        Optional one = userService.findOne(user);
        if(one.isPresent()){
            System.out.println(one.get().toString());
        }
    }

    @Test
    public void count(){
        User user = new User();
        user.setNickname("小八");
        long count = userService.count(user);
        System.out.println(count);
    }

    @Test
    public void exist(){
        User user = new User();
        user.setNickname("小八");
        boolean b = userService.exists(user);
        System.out.println(b);
        boolean b1 = userService.existsById(12);
        System.out.println(b1);
    }



}