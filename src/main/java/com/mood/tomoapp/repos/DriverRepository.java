package com.mood.tomoapp.repos;

import java.util.Optional;

import com.mood.tomoapp.domain.Driver;

/**
 * @author <a href="mailto:ales.justin@jboss.org">Ales Justin</a>
 */
public interface DriverRepository extends ActiveRepository<Driver> {
    Optional<Driver> findByDriverAndPasswordAndActiveIsGreaterThan(String driver, String password, int active);
}
