package com.xhf.demo.controller;

import com.xhf.demo.common.BaseContextHandler;
import com.xhf.demo.entity.User;
import com.xhf.demo.service.UserService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api(value = "用户管理接口")
@RestController
@RequestMapping("/user")
public class UserController extends BaseController<User, Integer> {

    @Autowired
    UserService userService;

    /**
     * 使用mybatis
     *
     * @param id
     * @return
     */
    @GetMapping("/getUserById")
    public User find11(Integer id) {
        return userService.xxx(id);
    }

    @GetMapping("/getUserName")
    public User findName() {
        return User.builder().username(BaseContextHandler.getUsername()).build();
    }

}
