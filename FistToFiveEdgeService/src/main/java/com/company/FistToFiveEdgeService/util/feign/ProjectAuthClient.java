//package com.company.FistToFiveEdgeService.util.feign;
//
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//
//import java.security.Principal;
//
//@FeignClient(name = "account-service")
//public interface ProjectAuthClient {
//
//    @PostMapping("/login")
//    public String getLogin(Principal principal);
//
//    @GetMapping("/studentDashboard")
//    public String viewStudentDashboard(Principal principal);
//
//    @GetMapping("/instructorDashboard")
//    public String viewInstructorDashboard(Principal principal);
//
//    @GetMapping("/publicDashboard")
//    public String viewPublicDashboard();
//
//    @GetMapping("/studentList")
//    public String showAllStudent(Principal principal);
//
//    @DeleteMapping("/activity/{id}")
//    public String deleteActivity(Principal principal, @PathVariable int id);
//
//    @GetMapping("/allDone")
//    public String calledByLogout();
//}
