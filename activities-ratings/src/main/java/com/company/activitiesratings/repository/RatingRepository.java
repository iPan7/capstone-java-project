package com.company.activitiesratings.repository;

import com.company.activitiesratings.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Integer> {
}
