package it.pkg.service.impl;

import it.pkg.entity.User;
import it.pkg.mapper.UserMapper;
import it.pkg.repository.UserRepository;
import it.pkg.service.UserService;
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
