package com.lxk.springboot.http;

import com.lxk.springboot.http.service.CoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Application 类
 *
 * @author LiXuekai on 2019/11/15
 */
@SpringBootApplication
public class Application {
    private static final Logger LOG = LoggerFactory.getLogger(Application.class);
    private static final String GET = "get";
    private static final String POST = "post";


    public static void main(String[] args) {
        try {
            final ConfigurableApplicationContext applicationContext = SpringApplication.run(Application.class, args);
            CoreService.initService(applicationContext);
            if (GET.equals(CoreService.config.getType())) {
                CoreService.httpService.httpGet();
            } else if (POST.equals(CoreService.config.getType())) {
                CoreService.httpService.httpPost2();
            }
        } catch (Exception e) {
            LOG.error("main error" + e.getMessage());
        }
    }


}
