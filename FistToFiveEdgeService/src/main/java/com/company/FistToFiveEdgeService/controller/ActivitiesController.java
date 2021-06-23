package com.company.FistToFiveEdgeService.controller;

import com.company.FistToFiveEdgeService.models.Activity;
import com.company.FistToFiveEdgeService.util.feign.ActivitiesRatingClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/activity")
public class ActivitiesController {

    @Autowired
    private final ActivitiesRatingClient client;

    public ActivitiesController(ActivitiesRatingClient client) {
        this.client = client;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Activity> getAllActivities() {
        return client.getAllActivities();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Activity getActivityById(@PathVariable int id) {
        return client.getActivityById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Activity createActivity(@RequestBody Activity activity) {
        return client.createActivity(activity);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateActivity(@PathVariable int id, @RequestBody Activity activity) {
        activity.setId(id);
        client.updateActivity(id, activity);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteActivity(@PathVariable int id) {
        client.deleteActivity(id);
    }
}
