package com.company.FistToFiveEdgeService.models;

import java.util.List;
import java.util.Objects;

public class Activity {

    public enum Status {
        PLANNED,
        INPROGRESS,
        COMPLETED
    }

    public enum Weekday {
        SUNDAY,
        MONDAY,
        TUESDAY,
        WEDNESDAY,
        THURSDAY,
        FRIDAY,
        SATURDAY
    }

    private Integer id;
    private String activityName;
    private String filePath;
    private String activityDescription;
    private Status status;
    private Weekday weekday;
    private List<Rating> ratings;

    public Activity(Integer id, String activityName, String filePath, String activityDescription, Status status, Weekday weekday, List<Rating> ratings) {
        this.id = id;
        this.activityName = activityName;
        this.filePath = filePath;
        this.activityDescription = activityDescription;
        this.status = status;
        this.weekday = weekday;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Weekday getWeekday() {
        return weekday;
    }

    public void setWeekday(Weekday weekday) {
        this.weekday = weekday;
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
        return Objects.equals(getId(), activity.getId()) && Objects.equals(getActivityName(), activity.getActivityName()) && Objects.equals(getFilePath(), activity.getFilePath()) && Objects.equals(getActivityDescription(), activity.getActivityDescription()) && getStatus() == activity.getStatus() && getWeekday() == activity.getWeekday() && Objects.equals(getRatings(), activity.getRatings());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getActivityName(), getFilePath(), getActivityDescription(), getStatus(), getWeekday(), getRatings());
    }

    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", activityName='" + activityName + '\'' +
                ", filePath='" + filePath + '\'' +
                ", activityDescription='" + activityDescription + '\'' +
                ", status=" + status +
                ", weekday=" + weekday +
                ", ratings=" + ratings +
                '}';
    }
}