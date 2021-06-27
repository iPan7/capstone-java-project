package com.company.activitiesratings.service;

import com.company.activitiesratings.model.Rating;
import com.company.activitiesratings.model.Rating;
import com.company.activitiesratings.repository.RatingRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class RatingsApiServiceLayerTest {

    @Autowired
    private RatingsApiServiceLayer service;

    private ObjectMapper mapper = new ObjectMapper();

    private Rating Happy;
    private Rating Sad;
    private Rating Wholesome;
    private List<Rating> ratings = new ArrayList<>();

    @Before
    public void setUp() throws Exception {


        RatingRepository mockRepo = mock(RatingRepository.class);

        Happy = new Rating(1, 2, 5, "Happy");
        Sad = new Rating(2, 1, 2, "This is another test description");
        ratings = new ArrayList<Rating>();
        ratings.add(Happy);
        ratings.add(Sad);

        doReturn(ratings).when(mockRepo).findAll();
        doReturn(Happy).when(mockRepo).getById(1);

        service = new RatingsApiServiceLayer(mockRepo);
    }

    @Test
    public void shouldReturnAllRatings() {
        // Arrange
        List<Rating> expectedOutput = new ArrayList<Rating>();
        expectedOutput.add(Happy);
        expectedOutput.add(Sad);

        // Act

        List<Rating> actualOutput = service.getAllRatings();

        // Assert

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void shouldReturnRatingById() {
        Rating expectedOutput = Happy;
        Rating actualOutput = service.getRatingById(1);
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void shouldReturnRatingOnCreation() {
        Rating inputWholesome = new Rating(3, 2, 4, "Wholesome");
        Rating actualOutput = service.createRating(inputWholesome);
        assertEquals(Wholesome, actualOutput);
    }
}