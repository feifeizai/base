package it.pkg.repository;

import it.pkg.entity.User;
import it.pkg.mapper.UserMapper;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends BaseRepository<User,Integer>{


}
