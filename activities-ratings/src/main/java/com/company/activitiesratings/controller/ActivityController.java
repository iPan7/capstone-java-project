package com.company.activitiesratings.controller;

import com.company.activitiesratings.model.Activity;
import com.company.activitiesratings.service.ActivitiesApiServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RefreshScope
@RequestMapping("/activity")
public class ActivityController {

    @Autowired
    ActivitiesApiServiceLayer service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Activity> getAllActivities() {
        return service.getAllActivities();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Activity getActivityById(@PathVariable int id) {
        return service.getActivityById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Activity createActivity(@RequestBody @Valid Activity activity) {
        return service.createActivity(activity);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateActivity(@PathVariable int id, @RequestBody @Valid Activity activity) {
        activity.setId(id);
        service.updateActivity(activity);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteActivity(@PathVariable int id) {
        service.deleteActivity(id);
    }

}
