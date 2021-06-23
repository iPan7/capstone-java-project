package com.company.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

public class AccountController {
    @RequestMapping(value="/studentDashboard", method= RequestMethod.GET)
    public String viewStudentDashboard(Principal principal) {
        return "Here is the student dashboard for " + principal.getName();
    }
    // private

    @RequestMapping(value="/instructorDashboard", method= RequestMethod.GET)
    public String viewInstructorDashboard(Principal principal) {
        return "Here is the instructor dashboard for " + principal.getName();
    }
    // private
    @RequestMapping(value="/publicDashboard", method= RequestMethod.GET)
    public String viewPublicDashboard() {
        return "Here is the public dashboard.";
    }

    @RequestMapping(value="/studentList", method=RequestMethod.GET)
    public String showAllStudents(Principal principal) {
        return "Hello, " + principal.getName() + ". You may view a list of event publishers.";
    }

    @RequestMapping(value="/activity/{id}", method=RequestMethod.DELETE)
    public String deleteActivity(Principal principal, @PathVariable int id) {
        return "You (" + principal.getName() + ") have deleted the activity with id " + id + ".";
    }

    @RequestMapping(value="/allDone", method=RequestMethod.GET)
    public String calledByLogout() {
        return "You are logged out.";
    }
}
