package com.gf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HelloService {

    @Autowired
    private RestTemplate restTemplate;

    public String hiService() {
        return restTemplate.getForObject( "http://SERVICE-HI/hi" , String.class );
    }

}
