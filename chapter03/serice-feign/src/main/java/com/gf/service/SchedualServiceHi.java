package com.gf.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "service-hi")
public interface SchedualServiceHi {

    @GetMapping("/hi")
    String sayHiFromClientOne();

}
