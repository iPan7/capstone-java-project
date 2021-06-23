package com.company.FistToFiveEdgeService.models;

import java.util.List;
import java.util.Objects;

public class Activity {

    private Integer id;
    private String activityName;
    private String filePath;
    private String activityDescription;
    private List<Rating> ratings;

    public Activity(Integer id, String activityName, String filePath, String activityDescription, List<Rating> ratings) {
        this.id = id;
        this.activityName = activityName;
        this.filePath = filePath;
        this.activityDescription = activityDescription;
        this.ratings = ratings;
    }

    public Activity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getActivityDescription() {
        return activityDescription;
    }

    public void setActivityDescription(String activityDescription) {
        this.activityDescription = activityDescription;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Activity)) return false;
        Activity activity = (Activity) o;
        return Objects.equals(getId(), activity.getId()) && Objects.equals(getActivityName(), activity.getActivityName()) && Objects.equals(getFilePath(), activity.getFilePath()) && Objects.equals(getActivityDescription(), activity.getActivityDescription()) && Objects.equals(getRatings(), activity.getRatings());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getActivityName(), getFilePath(), getActivityDescription(), getRatings());
    }

    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", activityName='" + activityName + '\'' +
                ", filePath='" + filePath + '\'' +
                ", activityDescription='" + activityDescription + '\'' +
                ", ratings=" + ratings +
                '}';
    }
}