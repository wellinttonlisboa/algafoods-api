package br.com.algaworks.algafoods.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.algaworks.algafoods.domain.Restaurant;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

	List<Restaurant> findByName(String name);
	List<Restaurant> findByNameContaining(String name);
	List<Restaurant> findByNameContainingAndKitchenNameContaining(String restaurantName
			,String kitchenName);
	List<Restaurant> findByNameContainingAndKitchenId(String restaurantName
			,Long kitchenId);
	List<Restaurant> findByFreight(Long freight);
	List<Restaurant> findByFreightBetween(Long startFreight
			,Long endFreight);
	
}
