package br.com.ecommerce.superstore.usuario.infraestrutura.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
@EnableCaching
public class CacheConfiguration {

    @Bean(name = "cache")
    public CacheManager cacheManager() {
        // configure and return an implementation of Spring's CacheManager SPI
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(Arrays.asList(new ConcurrentMapCache("UserDTO")));
        return cacheManager;
    }

}
