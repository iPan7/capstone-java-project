package com.company.FistToFiveEdgeService.util.feign;

import com.company.FistToFiveEdgeService.models.Activity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "Activities-Ratings")
public interface ActivitiesRatingClient {

    @GetMapping("/activity")
    public List<Activity> getAllActivities();

    @GetMapping("/activity/{id}")
    public Activity getActivityById(@PathVariable int id);

    @PostMapping("/activity")
    public Activity createActivity(@RequestBody Activity activity);

    @PutMapping("/activity/{id}")
    public void updateActivity(@PathVariable int id, @RequestBody Activity activity);

    @DeleteMapping("/activity/{id}")
    public void deleteActivity(@PathVariable int id);

}
