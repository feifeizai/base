#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service;

import ${package}.entity.User;
import org.springframework.transaction.annotation.Transactional;

public interface UserService extends BaseService<User,Integer>{

    User xxx(Integer id);
}
