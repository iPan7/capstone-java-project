package com.company.activitiesratings.service;

import com.company.activitiesratings.model.Rating;
import com.company.activitiesratings.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingsApiServiceLayer {

    RatingRepository ratingRepo;

    @Autowired
    public RatingsApiServiceLayer(RatingRepository repo) {
        this.ratingRepo = repo;
    }

    public List<Rating> getAllRatings() {
        return ratingRepo.findAll();
    }

    public Rating getRatingById(int id) {
        return ratingRepo.getById(id);
    }

    public Rating createRating(Rating rating) {
        return ratingRepo.save(rating);
    }

    public void updateRating(Rating rating) {
        ratingRepo.save(rating);
    }

    public void deleteRating(int id) {
        ratingRepo.deleteById(id);
    }
}
