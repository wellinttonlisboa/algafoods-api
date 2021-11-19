package br.com.algaworks.algafoods.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.algaworks.algafoods.domain.Restaurant;
import br.com.algaworks.algafoods.repository.impl.RestaurantRepositoryQueries;

@Repository
public interface RestaurantRepository extends CustomJpaRepository<Restaurant, Long>, RestaurantRepositoryQueries  {

	List<Restaurant> findByName(String name);
	List<Restaurant> findByNameContaining(String name);
	List<Restaurant> findByNameContainingAndKitchenNameContaining(String restaurantName
			,String kitchenName);
	List<Restaurant> findByNameContainingAndKitchenId(String restaurantName
			,Long kitchenId);
	
	List<Restaurant> caraiDeAsa(String restaurantName
			,Long kitchenId);
	
	List<Restaurant> findByFreight(Long freight);
	List<Restaurant> findByFreightBetween(Long startFreight
			,Long endFreight);
	
}
