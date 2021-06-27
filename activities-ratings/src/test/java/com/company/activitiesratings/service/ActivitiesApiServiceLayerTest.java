package com.company.activitiesratings.service;

import com.company.activitiesratings.model.Activity;
import com.company.activitiesratings.model.Rating;
import com.company.activitiesratings.repository.ActivityRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class ActivitiesApiServiceLayerTest {

    @Autowired
    private ActivitiesApiServiceLayer service;

    private Rating Ken;
    private Rating Ryu;
    private Activity Running;
    private Activity Swimming;
    private Activity Hunting;
    private List<Rating> userRatings;
    private List<Activity> activities;

    @Before
    public void setUp() throws Exception {


        ActivityRepository mockRepo = mock(ActivityRepository.class);

        Activity.Weekday weekday1 = Activity.Weekday.valueOf("SUNDAY");
        Activity.Status status1 = Activity.Status.valueOf("PLANNED");
        Activity.Weekday weekday2 = Activity.Weekday.valueOf("MONDAY");
        Activity.Status status2 = Activity.Status.valueOf("COMPLETED");
        Activity.Weekday weekday3 = Activity.Weekday.valueOf("FRIDAY");
        Activity.Status status3 = Activity.Status.valueOf("INPROGRESS");

        Ken = new Rating(1, 1, 5, "Ken");
        Ryu = new Rating(2, 1, 0, "Ryu");
        userRatings = new ArrayList<Rating>();
        userRatings.add(Ken);
        userRatings.add(Ryu);
        Running = new Activity(1, "Running", "Module 4, Day 3, Activity 2", "This is a test description", status1, weekday1, userRatings);
        Swimming = new Activity(2, "Swimming", "Module 1, Day 2, Activity 3", "This is another test description", status2, weekday2, userRatings);
        Hunting = new Activity( 3,"Hunting", "Module 2, Day 5, Activity 6", "This is the third test description", status3, weekday3, userRatings);
        Activity newHunting = new Activity(3, "Hunting", "Module 2, Day 5, Activity 6", "This is the third test description", status3, weekday3, userRatings);
        activities = new ArrayList<Activity>();
        activities.add(Running);
        activities.add(Swimming);

        doReturn(Running).when(mockRepo).getById(1);
        doReturn(activities).when(mockRepo).findAll();
        doReturn(Hunting).when(mockRepo).save(newHunting);
        doReturn(Hunting).when(mockRepo).save(Running);

        service = new ActivitiesApiServiceLayer(mockRepo);
    }

    @Test
    public void shouldReturnAllActivities() {
        // Arrange
        List<Activity> expectedOutput = new ArrayList<Activity>();
        expectedOutput.add(Running);
        expectedOutput.add(Swimming);

        // Act

        List<Activity> actualOutput = service.getAllActivities();

        // Assert

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void shouldReturnActivityById() {
        Activity expectedOutput = Running;
        Activity actualOutput = service.getActivityById(1);
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void shouldReturnActivityOnCreation() {
        Activity.Weekday weekday3 = Activity.Weekday.valueOf("FRIDAY");
        Activity.Status status3 = Activity.Status.valueOf("INPROGRESS");
        Activity inputHunting = new Activity(3,"Hunting", "Module 2, Day 5, Activity 6", "This is the third test description", status3, weekday3, userRatings);
        Activity actualOutput = service.createActivity(inputHunting);
        assertEquals(Hunting, actualOutput);
    }
}