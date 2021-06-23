package com.company.activitiesratings.controller;

import com.company.activitiesratings.model.Rating;
import com.company.activitiesratings.service.RatingsApiServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/rating")
public class RatingController {

    @Autowired
    RatingsApiServiceLayer service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Rating> getAllRatings() {
        return service.getAllRatings();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Rating getRatingById(@PathVariable int id) {
        return service.getRatingById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Rating createRating(@RequestBody @Valid Rating rating) {
        return service.createRating(rating);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateRating(@PathVariable int id, @RequestBody @Valid Rating rating) {
        rating.setId(id);
        service.updateRating(rating);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRating(@PathVariable int id) {
        service.deleteRating(id);
    }

}
