package com.avdemchenko.demo.app;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import java.time.LocalTime;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class CacheService {

    private final CacheManager cacheManager;

    @SneakyThrows
    @Cacheable(cacheNames = "ops", key = "'my-key:'+#name", unless = "#result.startsWith('aaa')")
    public String operation(final String name) {
        Thread.sleep(3000);
        return name + " - " + LocalTime.now().toString();
    }

    @CacheEvict(cacheNames = "ops", key = "'my-key:'+#name")
    public String clean(final String name) {
        return null;
    }

    @CachePut(cacheNames = "ops", key = "'my-key:'+#name")
    public String update(final String name) {
        return "HOHOHO";
    }

    @SneakyThrows
    @Cacheable(cacheNames = "op2", key = "'my-key:' + #name1 + ':' + #name2.length()")
    public String operation2(final String name1, final String name2) {
        Thread.sleep(1000);
        return name1 + " - " + name2 + " - " + LocalTime.now().toString();
    }
}
