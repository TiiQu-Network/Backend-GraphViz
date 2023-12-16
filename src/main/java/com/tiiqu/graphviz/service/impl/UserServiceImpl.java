package com.tiiqu.graphviz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tiiqu.graphviz.mapper.UserMapper;
import com.tiiqu.graphviz.model.User;
import com.tiiqu.graphviz.service.UserService;
import org.springframework.stereotype.Service;

/**
 * author : ALEXLIU
 * mail : ninphone@gmail.com
 * created : 12/13/2023, Wednesday
 **/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
