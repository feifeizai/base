package com.xhf.demo.service.impl;

import com.xhf.demo.entity.User;
import com.xhf.demo.mapper.UserMapper;
import com.xhf.demo.repository.UserRepository;
import com.xhf.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl extends BaseServiceImpl<User,Integer> implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    @Override
    public User xxx(Integer id){
       return userMapper.xxx(id);
    }

}
