package com.lxk.springboot.http.service;


import com.lxk.springboot.http.config.Config;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 初始化service
 *
 * @author SHEN on 2018/6/25
 */
public class CoreService {

    /**
     * 核心镜像库查询--投保单量
     */
    public static HttpService httpService;
    /**
     * es配置信息
     */
    public static Config config;

    public static void initService(ConfigurableApplicationContext applicationContext) {
        httpService = applicationContext.getBean("httpService", HttpService.class);
        config = applicationContext.getBean("config", Config.class);
    }
}
