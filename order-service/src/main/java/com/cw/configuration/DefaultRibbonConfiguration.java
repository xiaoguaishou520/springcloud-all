package com.cw.configuration;

import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.context.annotation.Configuration;

/**
 * @Author 小怪兽
 * @Date 2021-06-02
 */
@Configuration
@RibbonClients(defaultConfiguration = RibbonConfiguration.class)
public class DefaultRibbonConfiguration {
}
