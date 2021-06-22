package com.company.ActivitiesRatings.repository;

import com.company.ActivitiesRatings.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<Activity, Integer> {
}
