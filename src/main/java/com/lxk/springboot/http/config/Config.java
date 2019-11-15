package com.lxk.springboot.http.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author LiXuekai on 2019/11/15
 */
@Getter
@Service
public class Config {

    @Value("${type:get}")
    private String type = "get";

}
