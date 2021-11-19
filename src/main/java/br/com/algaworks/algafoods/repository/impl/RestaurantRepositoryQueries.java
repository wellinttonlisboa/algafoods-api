package br.com.algaworks.algafoods.repository.impl;

import java.util.List;

import br.com.algaworks.algafoods.domain.Restaurant;

public interface RestaurantRepositoryQueries {

	List<Restaurant> barrilDobrado(String name, Long startFreight, Long endFreight);
	
}
