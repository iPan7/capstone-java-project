package com.company.activitiesratings.controller;

        import com.company.activitiesratings.controller.ActivityController;
        import com.company.activitiesratings.model.Activity;
        import com.company.activitiesratings.model.Rating;
        import com.company.activitiesratings.service.ActivitiesApiServiceLayer;
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

        import static org.junit.Assert.*;
        import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
        import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
        import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(ActivityController.class)
@ImportAutoConfiguration(RefreshAutoConfiguration.class)
public class ActivityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper();

    @MockBean
    private ActivitiesApiServiceLayer mockService;

    private Activity Running;
    private Activity Swimming;
    private String runningJson;
    private List<Activity> allActivities = new ArrayList<>();
    private String allActivitiesJson;

    //         this.id = id;
    //        this.activityName = activityName;
    //        this.filePath = filePath;
    //        this.activityDescription = activityDescription;
    //        this.status = status;
    //        this.weekday = weekday;
    //        this.ratings = ratings;

    @Before
    public void setup() throws Exception {
        Rating Bob = new Rating();
        Rating Jim = new Rating();
        Activity.Weekday weekday1 = Activity.Weekday.valueOf("SUNDAY");
        Activity.Status status1 = Activity.Status.valueOf("PLANNED");
        Activity.Weekday weekday2 = Activity.Weekday.valueOf("MONDAY");
        Activity.Status status2 = Activity.Status.valueOf("COMPLETED");
        List<Rating> userRatings = new ArrayList<Rating>();
        userRatings.add(Bob);
        userRatings.add(Jim);
        Running = new Activity(1, "Running", "Module 4, Day 3, Activity 2", "This is a test description", status1, weekday1, userRatings);

        Swimming = new Activity(2, "Swimming", "Module 1, Day 2, Activity 3", "This is another test description", status2, weekday2, userRatings);

        runningJson = mapper.writeValueAsString(Running);

        allActivities.add(Running);
//        allActivities.add(Swimming);

        allActivitiesJson = mapper.writeValueAsString(allActivities);
    }

    @Test
    public void shouldReturn422WithInvalidBody() throws Exception {

        Activity inputActivity = new Activity();
        inputActivity.setActivityName("spring-testing-activity");
        inputActivity.setActivityDescription("this is a test description");
        String inputJson = mapper.writeValueAsString(inputActivity);

        mockMvc.perform(
                post("/activity").content(inputJson).contentType(MediaType.APPLICATION_JSON)
        ).andDo(print()).andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void shouldBeStatus200ForNonExistentActivityId() throws Exception {
        given(mockService.getActivityById(-1)).willReturn(null);
        mockMvc.perform(get("/activity/-1")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void shouldReturnAllActivitiesInCollection() throws Exception {
        given(mockService.getAllActivities()).willReturn(allActivities);

        mockMvc.perform(get("/activity"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(allActivitiesJson));
    }

    @Test
    public void shouldReturnActivityById() throws Exception {
        given(mockService.getActivityById(33)).willReturn(Running);

        mockMvc.perform(
                get("/activity/33")
        ).andExpect(status().isOk()).andExpect(content().json(runningJson));
    }

    @Test
    public void shouldCreateActivityOnPostRequest() throws Exception {
        List<Rating> userRatings = new ArrayList<Rating>();
        Activity.Weekday weekday1 = Activity.Weekday.valueOf("SUNDAY");
        Activity.Status status1 = Activity.Status.valueOf("PLANNED");
        Activity.Weekday weekday2 = Activity.Weekday.valueOf("MONDAY");
        Activity.Status status2 = Activity.Status.valueOf("COMPLETED");

        Activity Bison = new Activity(1, "Running", "Module 4, Day 3, Activity 2", "This is a test description", status1, weekday1, userRatings);
        Activity expectedBison = new Activity(2, "Swimming", "Module 1, Day 2, Activity 3", "This is another test description", status2, weekday2, userRatings);
        String inputBison = mapper.writeValueAsString(Bison);
        String outputBison = mapper.writeValueAsString(expectedBison);

        given(mockService.createActivity(Bison)).willReturn(expectedBison);

        mockMvc.perform(post("/activity").content(inputBison).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andExpect(content().json(outputBison));
    }

    @Test
    public void shouldUpdateByIdAndReturn204StatusCode() throws Exception {
        mockMvc.perform(
                put("/activity/1")
                        .content(runningJson).contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNoContent());
    }

    @Test
    public void shouldDeleteByIdAndReturn204StatusCode() throws Exception {
        mockMvc.perform(
                delete("/activity/1")
        ).andExpect(status().isNoContent());
    }
}