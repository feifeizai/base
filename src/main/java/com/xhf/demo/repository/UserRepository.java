package com.xhf.demo.repository;

import com.xhf.demo.entity.User;
import com.xhf.demo.mapper.UserMapper;
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
