package com.tiiqu.graphviz.controller;

import com.tiiqu.graphviz.common.CommonResult;
import com.tiiqu.graphviz.dto.UpdateGraphDTO;
import com.tiiqu.graphviz.dto.UserQueryDTO;
import com.tiiqu.graphviz.model.Graph;
import com.tiiqu.graphviz.model.GraphUpdateRecord;
import com.tiiqu.graphviz.model.Node;
import com.tiiqu.graphviz.model.User;
import com.tiiqu.graphviz.service.GraphUpdateService;
import com.tiiqu.graphviz.service.NodeService;
import com.tiiqu.graphviz.service.impl.GraphService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * author : ALEXLIU
 * mail : ninphone@gmail.com
 * created : 12/12/2023, Tuesday
 **/
@Api(tags="Graph")
@RestController
@RequestMapping("/graph")
@Slf4j
public class GraphController {

    @Autowired
    private GraphService graphService;

    @Autowired
    private GraphUpdateService graphUpdateService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private NodeService nodeService;

    @Operation(summary ="fetch latest graph")
    @GetMapping("/get")
    public CommonResult<List<Node>> getGraph()
    {
        System.out.println("fetch latest graph##########################################################");
        return CommonResult.success(nodeService.list());
    }

    @Operation(summary ="update graph")
    @PatchMapping("update")
    public CommonResult<UpdateGraphDTO> updateGraph(UpdateGraphDTO dto)
    {
        System.out.println("update latest graph##########################################################");
        graphService.updateGraph();
        GraphUpdateRecord gr = modelMapper.map(dto,GraphUpdateRecord.class);
        graphUpdateService.save(gr);
        return CommonResult.success(200,"graph updated");
    }

    @Operation(summary ="Query update history")
    @GetMapping("list")
    public CommonResult<List<GraphUpdateRecord>> list()
    {
        List<GraphUpdateRecord> list = graphUpdateService.list();
        return CommonResult.success(list);
    }
}
