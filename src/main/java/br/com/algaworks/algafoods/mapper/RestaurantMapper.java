package br.com.algaworks.algafoods.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.algaworks.algafoods.domain.Restaurant;
import br.com.algaworks.algafoods.requersts.RestaurantPostRequestBody;
import br.com.algaworks.algafoods.requersts.RestaurantPutRequestBody;

@Mapper(componentModel = "spring")
public abstract class RestaurantMapper {

    @Autowired
    public static final RestaurantMapper INSTANCE = Mappers.getMapper(RestaurantMapper.class);

    public abstract Restaurant toRestaurant(RestaurantPostRequestBody restaurantPostRequestBody);

    public abstract Restaurant toRestaurant(RestaurantPutRequestBody restaurantPostRequestBody);
}
