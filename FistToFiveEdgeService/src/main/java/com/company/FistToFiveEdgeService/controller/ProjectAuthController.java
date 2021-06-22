package com.company.FistToFiveEdgeService.controller;

import com.company.FistToFiveEdgeService.util.feign.ProjectAuthClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class ProjectAuthController {

    @Autowired
    private final ProjectAuthClient client;

    public ProjectAuthController(ProjectAuthClient client) {
        this.client = client;
    }

    @GetMapping("/login")
    public String getLogin() {
        return client.getLogin();
    }
}
