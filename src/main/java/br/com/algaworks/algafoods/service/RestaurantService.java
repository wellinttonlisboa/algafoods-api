package br.com.algaworks.algafoods.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.algaworks.algafoods.domain.Kitchen;
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

    public List<Restaurant> findByName(String name) {
        return restaurantRepository.findByName(name);
    }

    public Restaurant findByIdOrThrowBadRequestException(long id) {
        return restaurantRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Restaurant not Found"));
    }

    @Transactional
    public Restaurant save(RestaurantPostRequestBody restaurantPostRequestBody) {
        return restaurantRepository.save(RestaurantMapper.INSTANCE.toRestaurant(restaurantPostRequestBody));
    }

    @Transactional
    public void replace(RestaurantPutRequestBody restaurantPutRequestBody) {
        Restaurant savedRestaurant = findByIdOrThrowBadRequestException(restaurantPutRequestBody.getId());
        Restaurant restaurant = RestaurantMapper.INSTANCE.toRestaurant(restaurantPutRequestBody);
        restaurant.setId(savedRestaurant.getId());
        restaurantRepository.save(restaurant);
    }

    public void delete(long id) {
        restaurantRepository.delete(findByIdOrThrowBadRequestException(id));
    }

}
