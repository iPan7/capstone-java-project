package com.company.FistToFiveEdgeService.util.feign;

import com.company.FistToFiveEdgeService.models.Activity;
import com.company.FistToFiveEdgeService.models.Rating;
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

    @GetMapping("/rating")
    public List<Rating> getAllRating();

    @GetMapping("/rating/{id}")
    public Rating getRatingById(@PathVariable int id);

    @PostMapping("/rating")
    public Rating createRating(@RequestBody Rating rating);

    @PutMapping("/rating/{id}")
    public void updateRating(@PathVariable int id, @RequestBody Rating rating);

    @DeleteMapping("/rating/{id}")
    public void deleteRating(@PathVariable int id);

}
