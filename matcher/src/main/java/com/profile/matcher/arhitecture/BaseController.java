package com.profile.matcher.arhitecture;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseController extends BaseLogging {
    @Autowired
    protected BeanFactory beanFactory;

    protected <T> T getCommand(Class<T> commandType, Object... args) {
        return beanFactory.getBean(commandType, args);
    }
}
