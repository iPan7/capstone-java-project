package com.company.activitiesratings.controller;

import static org.junit.Assert.*;

import com.company.activitiesratings.model.Rating;
import com.company.activitiesratings.service.RatingsApiServiceLayer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.autoconfigure.RefreshAutoConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;

import javax.ws.rs.core.MediaType;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(RatingController.class)
@ImportAutoConfiguration(RefreshAutoConfiguration.class)
public class RatingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper();

    @MockBean
    private RatingsApiServiceLayer mockService;

    private Rating Happy;
    private Rating Sad;
    private String happyJson;
    private String allRatingsJson;
    private List<Rating> allRatings = new ArrayList<>();

    @Before
    public void setup() throws Exception {
        Happy = new Rating(1, 2, 5, "Happy");

        Sad = new Rating(2, 1, 2, "This is another test description");

        happyJson = mapper.writeValueAsString(Happy);

        allRatings.add(Happy);
        allRatings.add(Sad);

        allRatingsJson = mapper.writeValueAsString(allRatings);
    }

    @Test
    public void shouldReturn422WithInvalidBody() throws Exception {

        Rating inputRating = new Rating(4, 2, 5, "Cheese");
        inputRating.setRating(7);
        String inputJson = mapper.writeValueAsString(inputRating);

        mockMvc.perform(
                post("/rating").content(inputJson).contentType(MediaType.APPLICATION_JSON)
        ).andDo(print()).andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void shouldBeStatus200ForNonExistentRatingId() throws Exception {
        given(mockService.getRatingById(-1)).willReturn(null);
        mockMvc.perform(get("/rating/-1")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void shouldReturnAllRatingsInCollection() throws Exception {
        given(mockService.getAllRatings()).willReturn(allRatings);

        mockMvc.perform(get("/rating"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(allRatingsJson));
    }

    @Test
    public void shouldReturnRatingById() throws Exception {
        given(mockService.getRatingById(33)).willReturn(Happy);

        mockMvc.perform(
                get("/rating/33")
        ).andExpect(status().isOk()).andExpect(content().json(happyJson));
    }

    @Test
    public void shouldCreateRatingOnPostRequest() throws Exception {
        List<Rating> userRatings = new ArrayList<Rating>();

        Rating Bison = new Rating(15, 7, 0, "Bison");
        Rating expectedBison = new Rating(15, 7, 0, "Bison");
        String inputBison = mapper.writeValueAsString(Bison);
        String outputBison = mapper.writeValueAsString(expectedBison);

        given(mockService.createRating(Bison)).willReturn(expectedBison);

        mockMvc.perform(post("/rating").content(inputBison).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andExpect(content().json(outputBison));
    }

    @Test
    public void shouldUpdateByIdAndReturn204StatusCode() throws Exception {
        mockMvc.perform(
                put("/rating/1")
                        .content(happyJson).contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNoContent());
    }

    @Test
    public void shouldDeleteByIdAndReturn204StatusCode() throws Exception {
        mockMvc.perform(
                delete("/rating/1")
        ).andExpect(status().isNoContent());
    }
}