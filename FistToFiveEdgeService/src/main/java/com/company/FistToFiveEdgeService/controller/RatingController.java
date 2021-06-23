package com.company.FistToFiveEdgeService.controller;

import com.company.FistToFiveEdgeService.models.Rating;
import com.company.FistToFiveEdgeService.util.feign.ActivitiesRatingClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rating")
public class RatingController {

    @Autowired
    ActivitiesRatingClient client;

    public RatingController(ActivitiesRatingClient client) {
        this.client = client;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Rating> getAllRatings() {
        return client.getAllRating();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Rating getRatingById(@PathVariable int id) {
        return client.getRatingById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Rating createRating(@RequestBody Rating rating) {
        return client.createRating(rating);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateRating(@PathVariable int id, @RequestBody Rating rating) {
        rating.setId(id);
        client.updateRating(id, rating);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRating(@PathVariable int id) {
        client.deleteRating(id);
    }

}