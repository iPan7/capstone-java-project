package com.company.ActivitiesRatings.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;
import java.util.Set;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name="activity")
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotEmpty(message = "You must supply an activity name")
    private String activityName;
    @NotEmpty(message = "You must supply a file path")
    private String filePath;
    @NotEmpty(message = "You must supply a description")
    private String activityDescription;
    @OneToMany(mappedBy = "activityId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Rating> ratings;

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

    public Set<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(Set<Rating> ratings) {
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
