#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.controller;

import com.sun.xml.internal.bind.v2.model.core.ID;
import ${package}.entity.User;
import ${package}.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController<User,Integer>{

    /*@Autowired
    UserService userService;

    @RequestMapping("/find")
    public List<User> find(){
        return userService.findAll();
    }

    @PostMapping("/findType")
    public List<User> find(@RequestBody User user){
        return userService.findAll(user);
    }*/
    @Autowired
    UserService userService;

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/find/{id}")
    public User find(@PathVariable("id") Integer id){
        logger.info("查询方法，id："+id);
        Optional<User> optional = ${artifactId}Service.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }
        return null;
    }

    /**
     * 使用mybatis
     * @param id
     * @return
     */
    @GetMapping("/getUserById")
    public User find11(Integer id){
        return userService.xxx(id);
    }

}
