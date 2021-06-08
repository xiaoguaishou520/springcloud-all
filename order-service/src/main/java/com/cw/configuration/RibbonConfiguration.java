package com.cw.configuration;

import com.cw.rule.NacosSameClusterRule;
import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author 小怪兽
 * @Date 2021-06-02
 */
@Configuration
public class RibbonConfiguration {

    @Bean
    public IRule ribbonRule() {
        return new NacosSameClusterRule();
    }
}
