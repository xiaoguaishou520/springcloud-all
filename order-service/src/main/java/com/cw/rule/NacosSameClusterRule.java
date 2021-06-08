package com.cw.rule;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.alibaba.cloud.nacos.NacosServiceManager;
import com.alibaba.cloud.nacos.ribbon.NacosServer;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.alibaba.nacos.client.naming.utils.CollectionUtils;
import com.alibaba.nacos.common.utils.StringUtils;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.DynamicServerListLoadBalancer;
import com.netflix.loadbalancer.Server;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Author 小怪兽
 * @Date 2021-06-03
 */
@Slf4j
public class NacosSameClusterRule extends AbstractLoadBalancerRule {

    @Autowired
    private NacosDiscoveryProperties nacosDiscoveryProperties;

    @Autowired
    private NacosServiceManager nacosServiceManager;

    @Override
    public Server choose(Object key) {
        //1.获取到负载均衡器操作对象
        DynamicServerListLoadBalancer loadBalancer = (DynamicServerListLoadBalancer) getLoadBalancer();
        //2.获取到要访问的服务名称
        String serviceName = loadBalancer.getName();
        //3.获取到命名服务对象
        NamingService namingService = nacosServiceManager.getNamingService(nacosDiscoveryProperties.getNacosProperties());
        //4.获取到所有的服务节点
        try {
            List<Instance> instances = namingService.selectInstances(serviceName, true);
            if (CollectionUtils.isEmpty(instances)){
                log.warn("{} 没有找到对应的服务节点",serviceName);
                return null;
            }
            //5.获取当前服务的集群名称
            String clusterName = nacosDiscoveryProperties.getClusterName();
            //6.筛选同集群的服务节点
            List<Instance> chooseInstances = instances;
            if (StringUtils.isNotBlank(clusterName)){
                List<Instance> collect = instances.stream()
                        .filter(instance -> Objects.equals(clusterName,
                                instance.getClusterName())).collect(Collectors.toList());
                if (CollectionUtils.isEmpty(collect)) {
                    log.warn("服务:{} 集群：{} 存在跨集群访问的问 题", serviceName, clusterName);
                } else {
                    chooseInstances = collect;
                }
            }
            Instance instance = NacosBalancer.getHostByRandomWeight2(chooseInstances);
            return new NacosServer(instance);
        } catch (NacosException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {
    }

}
