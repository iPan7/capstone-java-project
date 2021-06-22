package com.company.FistToFiveEdgeService.util.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "account-service")
public interface ProjectAuthClient {

    @GetMapping("/login")
    public String getLogin();
}
