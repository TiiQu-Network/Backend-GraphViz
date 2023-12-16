package com.tiiqu.graphviz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tiiqu.graphviz.mapper.GraphUpdateMapper;
import com.tiiqu.graphviz.model.GraphUpdateRecord;
import com.tiiqu.graphviz.service.GraphUpdateService;
import org.springframework.stereotype.Service;

/**
 * author : ALEXLIU
 * mail : ninphone@gmail.com
 * created : 12/13/2023, Wednesday
 **/
@Service
public class GraphUpdateServiceImpl extends ServiceImpl<GraphUpdateMapper, GraphUpdateRecord> implements GraphUpdateService {
}
