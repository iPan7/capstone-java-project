package com.company.FistToFiveEdgeService.util.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "Activities-Ratings")
public interface ActivitiesRatingClient {

    @GetMapping("/rating")
    public String getRating();
}
