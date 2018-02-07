/*
 * Copyright 2016-2017 Red Hat, Inc, and individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mood.tomoapp;

import java.util.Arrays;
import java.util.Collections;

import com.mood.tomoapp.cache.ActiveableKeyGenerator;
import com.mood.tomoapp.cache.TempCacheManager;
import com.mood.tomoapp.mvc.ApiFilter;
import com.mood.tomoapp.mvc.AuthFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
@EnableCaching
@EnableScheduling
public class TomoApplication {

    @Value("${api.token}")
    private String apiToken;

    public static void main(String[] args) {
        SpringApplication.run(TomoApplication.class, args);
    }

    @Bean
    public FilterRegistrationBean authFilter() {
        FilterRegistrationBean frb = new FilterRegistrationBean(new AuthFilter());
        frb.setUrlPatterns(Collections.singleton("/view/*"));
        return frb;
    }

    @Bean
    public FilterRegistrationBean apiFilter() {
        FilterRegistrationBean frb = new FilterRegistrationBean(new ApiFilter(apiToken));
        frb.setUrlPatterns(Arrays.asList("/api/*", "/crud/*"));
        return frb;
    }

    @Bean
    public CacheManager cacheManager() {
        return new TempCacheManager();
    }

    @Bean
    public KeyGenerator keyGenerator() {
        return new ActiveableKeyGenerator();
    }
}
