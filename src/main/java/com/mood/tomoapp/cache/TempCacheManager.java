package com.mood.tomoapp.cache;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.cache.Cache;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;

/**
 * @author <a href="mailto:ales.justin@jboss.org">Ales Justin</a>
 */
public class TempCacheManager extends ConcurrentMapCacheManager {
    private final ConcurrentMap<String, ConcurrentMap<Object, Object>> maps = new ConcurrentHashMap<>();

    public void purge() {
        Set<ConcurrentMap> set = new HashSet<>(maps.values());
        for (ConcurrentMap cm : set) {
            cm.clear();
        }
    }

    protected ConcurrentMap<Object, Object> createConcurrentMap(String name) {
        ConcurrentMap<Object, Object> map = new ConcurrentHashMap<>();
        ConcurrentMap<Object, Object> previous = maps.putIfAbsent(name, map);
        return (previous != null ? previous : map);
    }

    @Override
    protected Cache createConcurrentMapCache(String name) {
        return new ConcurrentMapCache(name, createConcurrentMap(name), false);
    }
}
