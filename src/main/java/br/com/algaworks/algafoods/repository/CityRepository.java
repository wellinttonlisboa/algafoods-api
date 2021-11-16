package br.com.algaworks.algafoods.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.algaworks.algafoods.domain.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

	List<City> findByName(String name);
	List<City> findByNameContaining(String name);
	List<City> findByNameContainingAndStateNameContaining(String cityName
			,String stateName);
	List<City> findByNameContainingAndStateId(String name
			, Long stateId);
	
}
