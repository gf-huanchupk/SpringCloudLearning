package com.gf;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class HelloController {

    @GetMapping("/hi")
    public String hi() {
        return "hello ~";
    }

    @GetMapping("/hey")
    public String hey() {
        return "hey ~";
    }


    @GetMapping("/oh")
    public String oh() {
        return "oh ~";
    }

    @GetMapping("/ah")
    public String ah() {
        //模拟接口1/3的概率超时
        Random rand = new Random();
        int randomNum = rand.nextInt(3) + 1;
        if (3 == randomNum) {
            try {
                Thread.sleep( 3000 );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return "来了老弟~";
    }

}
