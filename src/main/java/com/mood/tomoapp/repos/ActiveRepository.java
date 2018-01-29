package com.mood.tomoapp.repos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author <a href="mailto:ales.justin@jboss.org">Ales Justin</a>
 */
@NoRepositoryBean
public interface ActiveRepository<T> extends CrudRepository<T, Integer> {
    List<T> findByActiveIsGreaterThan(int active);
}
