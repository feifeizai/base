package it.pkg.service;

import it.pkg.entity.User;
import org.springframework.transaction.annotation.Transactional;

public interface UserService extends BaseService<User,Integer>{

    User xxx(Integer id);
}
