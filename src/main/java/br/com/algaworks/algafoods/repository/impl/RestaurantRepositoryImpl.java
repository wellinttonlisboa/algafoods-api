package br.com.algaworks.algafoods.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.algaworks.algafoods.domain.Restaurant;

@Repository
public class RestaurantRepositoryImpl implements RestaurantRepositoryQueries {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Restaurant> barrilDobrado(String name, Long startFreight, Long endFreight) {
		var criteriaBuilder = manager.getCriteriaBuilder();
		
		var criteriaQuery = criteriaBuilder.createQuery(Restaurant.class);
		var root = criteriaQuery.from(Restaurant.class);

		var nameLikePredicate = criteriaBuilder.like(root.get("name"), "%".concat(name).concat("%"));
		var startFreightPredicate = criteriaBuilder.greaterThanOrEqualTo(root.get("freight")
				, startFreight);
		var endFreightPredicate = criteriaBuilder.lessThanOrEqualTo(root.get("freight")
				, endFreight);
		
		criteriaQuery.where(nameLikePredicate
				, startFreightPredicate
				, endFreightPredicate);
		
		var query = manager.createQuery(criteriaQuery);
		return query.getResultList();
	}
	
}
