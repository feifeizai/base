package com.xhf.demo.service;

import com.xhf.demo.entity.User;
import org.springframework.transaction.annotation.Transactional;

public interface UserService extends BaseService<User,Integer>{

    User xxx(Integer id);
}
