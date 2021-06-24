package com.company.FistToFiveEdgeService.controller;

import com.company.FistToFiveEdgeService.util.feign.ProjectAuthClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RefreshScope
public class ProjectAuthController {

    @Autowired
    private final ProjectAuthClient client;

    public ProjectAuthController(ProjectAuthClient client) {
        this.client = client;
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public String getLogin(Principal principal) {
        return client.getLogin(principal);
    }

    @GetMapping("/studentDashboard")
    @ResponseStatus(HttpStatus.OK)
    public String viewStudentDashboard(Principal principal) {
        return client.viewStudentDashboard(principal);
    }

    @GetMapping("/instructorDashboard")
    @ResponseStatus(HttpStatus.OK)
    public String viewInstructorDashboard(Principal principal) {
        return client.viewInstructorDashboard(principal);
    }

    @GetMapping("/publicDashboard")
    @ResponseStatus(HttpStatus.OK)
    public String viewPublicDashboard() {
        return client.viewPublicDashboard();
    }

    @GetMapping("/studentList")
    @ResponseStatus(HttpStatus.OK)
    public String showAllStudent(Principal principal) {
        return client.showAllStudent(principal);
    }

    @GetMapping("/allDone")
    @ResponseStatus(HttpStatus.OK)
    public String calledByLogout() {
        return client.calledByLogout();
    }
}
