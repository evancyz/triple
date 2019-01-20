package com.piaoniu.service.demo.goods.config;

import com.evanyz.triple.core.provider.serviceDiscoverer.ProviderServiceDiscoverer;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Created by evan on 2019/1/20.
 */
@Component
public class TripleSpringServiceDiscoverer extends ProviderServiceDiscoverer implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override public Object discovery(String serviceName, Class aClass) {
        return applicationContext.getBean(aClass);
    }

    @Override public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        this.applicationContext = applicationContext;

    }
}
