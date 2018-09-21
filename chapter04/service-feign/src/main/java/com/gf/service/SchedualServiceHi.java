package com.gf.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "service-hi", fallback = HystrixClientFallback.class)
public interface SchedualServiceHi {

    @GetMapping("/hi")
    String sayHiFromClientOne();

}
