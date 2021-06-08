package com.cw.rule;

import com.alibaba.nacos.api.naming.pojo.Instance;
import com.alibaba.nacos.client.naming.core.Balancer;

import java.util.List;

/**
 * @Author 小怪兽
 * @Date 2021-06-03
 */
public class NacosBalancer extends Balancer {

    public static Instance getHostByRandomWeight2(List<Instance> hosts) {
        return getHostByRandomWeight(hosts);
    }
}
