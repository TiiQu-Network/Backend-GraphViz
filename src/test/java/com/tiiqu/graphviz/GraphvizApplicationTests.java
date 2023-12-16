package com.tiiqu.graphviz;

import com.tiiqu.graphviz.mapper.UserMapper;
import com.tiiqu.graphviz.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.tiiqu.graphviz.model.User;

import java.util.List;

@SpringBootTest
class GraphvizApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @Test
    void contextLoads() {
    }

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        for(User user:userList) {
            System.out.println(user);
        }
    }
    @Test
    public void testSelect2() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userService.list();
        userList.forEach(System.out::println);
    }
}
