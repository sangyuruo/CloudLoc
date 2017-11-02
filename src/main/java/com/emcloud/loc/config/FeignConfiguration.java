package com.emcloud.loc.config;

import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "com.emcloud.loc")
public class FeignConfiguration {

}
