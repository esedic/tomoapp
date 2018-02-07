package com.mood.tomoapp.repos;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author <a href="mailto:ales.justin@jboss.org">Ales Justin</a>
 */
@NoRepositoryBean
public interface ActiveRepository<T> extends CrudRepository<T, Integer> {
    @Cacheable(value = "activeable", keyGenerator = "keyGenerator")
    List<T> findByActiveIsGreaterThan(int active, Sort sort);
}
