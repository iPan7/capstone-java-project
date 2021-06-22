package com.company.ActivitiesRatings.repository;

import com.company.ActivitiesRatings.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Integer> {
}
