package com.company.activitiesratings.service;

import com.company.activitiesratings.model.Activity;
import com.company.activitiesratings.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivitiesApiServiceLayer {

    ActivityRepository activityRepo;

    @Autowired
    public ActivitiesApiServiceLayer(ActivityRepository repo) {
        this.activityRepo = repo;
    }

    public List<Activity> getAllActivities() {
        return activityRepo.findAll();
    }

    public Activity getActivityById(int id) {
        return activityRepo.getById(id);
    }

    public Activity createActivity(Activity activity) {
        return activityRepo.save(activity);
    }

    public void updateActivity(Activity activity) {
        activityRepo.save(activity);
    }

    public void deleteActivity(int id) {
        activityRepo.deleteById(id);
    }

//    public BigDecimal calculateAverageRating(List<Integer> activityIds) {
//        BigDecimal returnVal = new BigDecimal("0");
//        for (Integer activityId : activityIds) {
//            Activity thisActivity = activityRepo.getById(activityId);
//            returnVal = returnVal.add(thisActivity.getRatings());
//        }
//
//        return activityIds.size() < 5 ? returnVal : (returnVal.multiply(new BigDecimal("0.9")).setScale(2, RoundingMode.HALF_UP));
//    }
}
