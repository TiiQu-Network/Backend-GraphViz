package com.tiiqu.graphviz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tiiqu.graphviz.mapper.NodeMapper;
import com.tiiqu.graphviz.model.Node;
import com.tiiqu.graphviz.service.NodeService;
import org.springframework.stereotype.Service;

/**
 * author : ALEXLIU
 * mail : ninphone@gmail.com
 * created : 12/13/2023, Wednesday
 **/
@Service
public class NodeServiceImpl extends ServiceImpl<NodeMapper, Node> implements NodeService {
}
