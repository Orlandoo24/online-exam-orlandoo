package com.exam.service;

import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public interface RedisService {

    public <T> void setCacheObject(final String key, final T value, final Long timeout, final TimeUnit timeUnit);

}
