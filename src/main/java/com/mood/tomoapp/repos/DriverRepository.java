package com.mood.tomoapp.repos;

import java.util.Optional;

import com.mood.tomoapp.domain.Driver;
import org.springframework.data.repository.CrudRepository;

/**
 * @author <a href="mailto:ales.justin@jboss.org">Ales Justin</a>
 */
public interface DriverRepository extends CrudRepository<Driver, Integer> {
    Optional<Driver> findByDriverAndPassword(String driver, String password);
}
