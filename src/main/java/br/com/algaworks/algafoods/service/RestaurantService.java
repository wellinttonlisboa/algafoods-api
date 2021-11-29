package br.com.algaworks.algafoods.service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.algaworks.algafoods.domain.Restaurant;
import br.com.algaworks.algafoods.exception.BadRequestException;
import br.com.algaworks.algafoods.mapper.RestaurantMapper;
import br.com.algaworks.algafoods.repository.RestaurantRepository;
import br.com.algaworks.algafoods.requersts.RestaurantPostRequestBody;
import br.com.algaworks.algafoods.requersts.RestaurantPutRequestBody;

@Service
public class RestaurantService {

	@Autowired
	private RestaurantRepository restaurantRepository;

	public Page<Restaurant> listAll(Pageable pageable) {
		return restaurantRepository.findAll(pageable);
	}

	public List<Restaurant> listAllNonPageable() {
		return restaurantRepository.findAll();
	}

	public Restaurant findByIdOrThrowBadRequestException(Long id) {
		return restaurantRepository.findById(id).orElseThrow(() -> new BadRequestException("Restaurant not Found", null));
	}
	
	public List<Restaurant> findByName(String name) {
		return restaurantRepository.findByName(name);
	}

	public List<Restaurant> findByNameContaining(String name) {
		return restaurantRepository.findByNameContaining(name);
	}
	
	public List<Restaurant> findByNameContainingAndKitchenNameContaining(String restaurantName
			,String kitchenName) {
		return restaurantRepository.findByNameContainingAndKitchenNameContaining(restaurantName, kitchenName);
	}
	
	public List<Restaurant> findByNameContainingAndKitchenId(String restaurantName
			,Long kitchenId) {
		return restaurantRepository.findByNameContainingAndKitchenId(restaurantName, kitchenId);
	}
	
	public List<Restaurant> caraiDeAsa(String restaurantName
			,Long kitchenId) {
		return restaurantRepository.caraiDeAsa(restaurantName, kitchenId);
	}
	
	public List<Restaurant> barrilDobrado(String name, Long startFreight, Long endFreight) {
		return restaurantRepository.barrilDobrado(name, startFreight, endFreight);
	}
	
	public Optional<Restaurant> customBuscarPrimeiro() {
		return restaurantRepository.buscarPrimeiro();
	}
	
	
	public List<Restaurant> findByFreight(Long freight) {
		return restaurantRepository.findByFreight(freight);
	}
	
	public List<Restaurant> findByFreightBetween(Long startFreight
			,Long endFreight) {
		return restaurantRepository.findByFreightBetween(startFreight, endFreight);
	}

	@Transactional
	public Restaurant save(RestaurantPostRequestBody restaurantPostRequestBody) {
		try {
			return restaurantRepository.save(RestaurantMapper.INSTANCE.toRestaurant(restaurantPostRequestBody));
		} catch (DataIntegrityViolationException | EntityNotFoundException e) {
			throw new BadRequestException("The Restaurant cannot be saved", null);
		}
	}

	@Transactional
	public Restaurant replacePartial(Long id, Map<String, Object> patchRequestBody) {
        Restaurant updatedRestaurant = findByIdOrThrowBadRequestException(id);
        
        ObjectMapper objectMapper = new ObjectMapper();
        Restaurant restaurant = objectMapper.convertValue(patchRequestBody, Restaurant.class);
        
        patchRequestBody.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Restaurant.class, key);
            field.setAccessible(Boolean.TRUE);
            Object newValue = ReflectionUtils.getField(field, restaurant);
            ReflectionUtils.setField(field, updatedRestaurant, newValue);
        });
        
        return restaurantRepository.save(updatedRestaurant);
	}
	
	@Transactional
	public void replace(RestaurantPutRequestBody restaurantPutRequestBody) {
		Restaurant savedRestaurant = findByIdOrThrowBadRequestException(restaurantPutRequestBody.getId());
		Restaurant restaurant = RestaurantMapper.INSTANCE.toRestaurant(restaurantPutRequestBody);
		restaurant.setId(savedRestaurant.getId());
		
		
		
		
		restaurant.setPayments(savedRestaurant.getPayments());
		restaurant.setProducts(savedRestaurant.getProducts());
		restaurant.setAddress(savedRestaurant.getAddress());
		restaurant.setCreatedAt(savedRestaurant.getCreatedAt());

		try {
			restaurantRepository.save(restaurant);
		} catch (EntityNotFoundException | JpaObjectRetrievalFailureException e) {
			throw new BadRequestException("The Restaurant cannot be saved", null);
		}
	}

	public void delete(Long id) {
		try {
			restaurantRepository.delete(findByIdOrThrowBadRequestException(id));
		} catch (DataIntegrityViolationException e) {
			throw new BadRequestException("The Restaurant cannot be removed. Ist is in use", null);
		}
	}

}