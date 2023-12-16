package com.tiiqu.graphviz.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * author : ALEXLIU
 * mail : ninphone@gmail.com
 * created : 12/13/2023, Wednesday
 **/
@Data
@TableName(value = "graph_update_record")
public class GraphUpdateRecord {
    @TableId(type = IdType.AUTO)
    Integer id;

    private String source;

    private String description;

    private Date updated;
}
