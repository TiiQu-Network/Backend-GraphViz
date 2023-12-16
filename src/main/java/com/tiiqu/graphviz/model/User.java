package com.tiiqu.graphviz.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;

/**
 * author : ALEXLIU
 * mail : ninphone@gmail.com
 * created : 12/12/2023, Tuesday
 **/
@Data
//@ApiModel("user")
@TableName(value = "viz_user")
public class User {

//    @ApiModelProperty("id")
    @TableId(type = IdType.AUTO)
    Integer id;

//    @ApiModelProperty("name")
    private String username;

//    @ApiModelProperty("password")
    private String password;

//    @ApiModelProperty("email")
    private String email;

//    @ApiModelProperty("create time")
    private Date created;
}