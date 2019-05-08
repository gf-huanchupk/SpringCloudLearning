package com.gf.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HelloService {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 简单用法
     */
    @HystrixCommand
    public String hiService() {
        return restTemplate.getForObject("http://SERVICE-HI/hi" , String.class);
    }

    /**
     * 定制超时
     */
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "30000") })
    public String heyService() {
        return restTemplate.getForObject("http://SERVICE-HI/hey" , String.class);
    }

    /**
     * 定制降级方法
     */
    @HystrixCommand(fallbackMethod = "getFallback")
    public String ahService() {
        return restTemplate.getForObject("http://SERVICE-HI/ah" , String.class);
    }

    /**
     * 定制线程池隔离策略
     */
    @HystrixCommand(fallbackMethod = "getFallback",
            threadPoolKey = "studentServiceThreadPool",
            threadPoolProperties = {
                    @HystrixProperty(name="coreSize", value="30"),
                    @HystrixProperty(name="maxQueueSize", value="50")
            }
    )
    public String ohService() {
        return restTemplate.getForObject("http://SERVICE-HI/oh" , String.class);
    }


    public String getFallback() {
        return "Oh , sorry , error !";
    }


}
