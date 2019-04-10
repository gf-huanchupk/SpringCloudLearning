package com.gf;

import com.dianping.cat.Cat;
import com.dianping.cat.message.Event;
import com.dianping.cat.message.Transaction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@SpringBootApplication
@RestController
public class CatSimpleApplication {


    public static void main(String[] args) {
        SpringApplication.run( CatSimpleApplication.class, args );
    }

    @PostMapping("/hi")
    public String hi(HttpServletRequest request){
        String url = request.getRequestURL().toString();

        // 创建一个 Transaction
        Transaction transaction = Cat.newTransaction( "URL", url );
        try {
            // 处理业务
            myBusiness();
            // 设置状态
            transaction.setStatus(Transaction.SUCCESS);
        } catch (Exception e) {
            // 设置错误状态
            transaction.setStatus(e);
            // 记录错误信息
            Cat.logError(e);
        } finally {
            // 结束 Transaction
            transaction.complete();
        }

        return "hello";
    }

    @PostMapping("/error")
    public String error(HttpServletRequest request){
        String url = request.getRequestURL().toString();

        // 创建一个 Transaction
        Transaction transaction = Cat.newTransaction( "URL", url );
        try {
            // 处理业务
            int i = 1 / 0;
            // 设置状态
            transaction.setStatus(Transaction.SUCCESS);
        } catch (Exception e) {
            // 设置错误状态
            transaction.setStatus(e);
            // 记录错误信息
            Cat.logError(e);
        } finally {
            // 结束 Transaction
            transaction.complete();
        }

        return "500";
    }

    private void myBusiness() {
        //模拟业务处理的时间
        try {
            Thread.sleep( 500 );
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



}
