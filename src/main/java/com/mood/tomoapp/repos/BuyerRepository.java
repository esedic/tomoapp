package com.mood.tomoapp.repos;

import com.mood.tomoapp.domain.Buyer;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author <a href="mailto:ales.justin@jboss.org">Ales Justin</a>
 */
public interface BuyerRepository extends PagingAndSortingRepository<Buyer, Integer> {
}
