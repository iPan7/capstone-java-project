package com.company.FistToFiveEdgeService.controller;

import com.company.FistToFiveEdgeService.util.feign.ActivitiesRatingClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class ActivitiesRatingController {

    @Autowired
    private final ActivitiesRatingClient client;


    public ActivitiesRatingController(ActivitiesRatingClient client) {
        this.client = client;
    }

    @GetMapping('/rating')
    public String getRating() {
        return client.getRating();
    }
}
