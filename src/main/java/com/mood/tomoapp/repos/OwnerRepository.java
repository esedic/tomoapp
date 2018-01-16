package com.mood.tomoapp.repos;

import com.mood.tomoapp.domain.Owner;
import org.springframework.data.repository.CrudRepository;

/**
 * @author <a href="mailto:ales.justin@jboss.org">Ales Justin</a>
 */
public interface OwnerRepository extends CrudRepository<Owner, Integer> {
}
