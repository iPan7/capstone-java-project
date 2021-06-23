package com.company.FistToFiveEdgeService.models;

import java.util.Objects;

public class Rating {

    private Integer id;
    private Integer activityId;
    private Integer rating;
    private String username;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rating)) return false;
        Rating rating1 = (Rating) o;
        return Objects.equals(getId(), rating1.getId()) && Objects.equals(getActivityId(), rating1.getActivityId()) && Objects.equals(getRating(), rating1.getRating()) && Objects.equals(getUsername(), rating1.getUsername());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getActivityId(), getRating(), getUsername());
    }

    @Override
    public String toString() {
        return "Rating{" +
                "id=" + id +
                ", activityId=" + activityId +
                ", rating=" + rating +
                ", username='" + username + '\'' +
                '}';
    }
}
