package com.gf.service;


        import org.springframework.stereotype.Component;

@Component
public class HystrixClientFallback implements SchedualServiceHi{
    @Override
    public String sayHiFromClientOne() {
        return "sorry , server error";
    }
}
