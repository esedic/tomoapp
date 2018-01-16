package com.mood.tomoapp.repos;

import com.mood.tomoapp.domain.Fueling;
import org.springframework.data.repository.CrudRepository;

/**
 * @author <a href="mailto:ales.justin@jboss.org">Ales Justin</a>
 */
public interface FuelingRepository extends CrudRepository<Fueling, Integer> {
}
