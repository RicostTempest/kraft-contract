package com.windsoft.kraft.contract.server.flow.controller;

import com.windsoft.kraft.contract.common.utils.JsonResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("flow")
public class FlowController {

    @PostMapping("project/{projectId}")
    public JsonResult createProjectFlow(){
        return null;
    }
}
