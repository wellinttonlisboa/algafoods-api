package br.com.algaworks.algafoods.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.mapstruct.BeanMapping;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.algaworks.algafoods.domain.Restaurant;
import br.com.algaworks.algafoods.exception.BadRequestException;
import br.com.algaworks.algafoods.mapper.RestaurantMapper;
import br.com.algaworks.algafoods.repository.RestaurantRepository;
import br.com.algaworks.algafoods.requersts.RestaurantPostRequestBody;
import br.com.algaworks.algafoods.requersts.RestaurantPutRequestBody;
import ch.qos.logback.core.joran.util.beans.BeanUtil;

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

	public List<Restaurant> findByName(String name) {
		return restaurantRepository.findByName(name);
	}

	public Restaurant findByIdOrThrowBadRequestException(long id) {
		return restaurantRepository.findById(id).orElseThrow(() -> new BadRequestException("Restaurant not Found"));
	}

	@Transactional
	public Restaurant save(RestaurantPostRequestBody restaurantPostRequestBody) {
		try {
			return restaurantRepository.save(RestaurantMapper.INSTANCE.toRestaurant(restaurantPostRequestBody));
		} catch (DataIntegrityViolationException e) {
			throw new BadRequestException("The Restaurant cannot be saved");
		} catch (EntityNotFoundException e) {
			throw new BadRequestException("The Restaurant cannot be saved");
		}
	}

	@Transactional
	public void replace(RestaurantPutRequestBody restaurantPutRequestBody) {
		Restaurant savedRestaurant = findByIdOrThrowBadRequestException(restaurantPutRequestBody.getId());
		Restaurant restaurant = RestaurantMapper.INSTANCE.toRestaurant(restaurantPutRequestBody);
		restaurant.setId(savedRestaurant.getId());

		try {
			restaurantRepository.save(restaurant);
		} catch (EntityNotFoundException | JpaObjectRetrievalFailureException e) {
			throw new BadRequestException("The Restaurant cannot be saved");
		}
	}

	@Transactional
	public void replacePartial(RestaurantPutRequestBody restaurantPutRequestBody) {
		Restaurant savedRestaurant = findByIdOrThrowBadRequestException(restaurantPutRequestBody.getId());
		Restaurant restaurant = RestaurantMapper.INSTANCE.toRestaurant(restaurantPutRequestBody);
		BeanUtils.copyProperties(restaurant, savedRestaurant);
	    System.out.println(restaurant);
	    System.out.println(savedRestaurant);
		//restaurant.setId(savedRestaurant.getId());

		try {
			restaurantRepository.save(savedRestaurant);
		} catch (EntityNotFoundException | JpaObjectRetrievalFailureException e) {
			throw new BadRequestException("The Restaurant cannot be saved");
		}
	}
	
	public void delete(long id) {
		try {
			restaurantRepository.delete(findByIdOrThrowBadRequestException(id));
		} catch (DataIntegrityViolationException e) {
			throw new BadRequestException("The Restaurant cannot be removed it is in use");
		}
	}

}
