package com.mood.tomoapp.repos;

import com.mood.tomoapp.domain.Truck;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author <a href="mailto:ales.justin@jboss.org">Ales Justin</a>
 */
public interface TruckRepository extends PagingAndSortingRepository<Truck, Integer> {
}
