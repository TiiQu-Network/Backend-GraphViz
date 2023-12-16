package com.tiiqu.graphviz.controller;

import java.util.ArrayList;
import java.util.List;
import com.tiiqu.graphviz.common.CommonResult;
import com.tiiqu.graphviz.dto.UserCreateDTO;
import com.tiiqu.graphviz.dto.UserQueryDTO;
import com.tiiqu.graphviz.model.User;
import com.tiiqu.graphviz.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * author : ALEXLIU
 * mail : ninphone@gmail.com
 * created : 12/13/2023, Wednesday
 **/

@Api(tags="User")
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * @param user user param
     * @return user
     */
    @Operation(summary ="Add/Edit User")
    @PostMapping("add")
    public CommonResult<UserCreateDTO> add(UserCreateDTO dto) {
        User user = modelMapper.map(dto,User.class);
//        log.info(user.toString());
        return userService.save(user)? CommonResult.success(200,"a new user added"):CommonResult.error(400,"failed to add a user");
    }


    /**
     * @return user list
     */
    @Operation(summary ="Query User One")
    @GetMapping("edit/{userId}")
    public CommonResult<User> edit(@PathVariable("userId") int userId) {
        return CommonResult.success(userService.getById(userId));
    }

    /**
     * @return user list
     */
    @Operation(summary ="Query User List")
    @GetMapping("list")
    public CommonResult<List<UserQueryDTO>> list()
    {
        List<User> listUser = userService.list();
        List<UserQueryDTO> listDTO = new ArrayList<>();
        for(User u:listUser)
        {
            UserQueryDTO ud = modelMapper.map(u,UserQueryDTO.class);
            listDTO.add(ud);
        }
        return CommonResult.success(listDTO);
    }
}
