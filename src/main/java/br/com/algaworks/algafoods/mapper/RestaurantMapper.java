package br.com.algaworks.algafoods.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import br.com.algaworks.algafoods.domain.Restaurant;
import br.com.algaworks.algafoods.requersts.RestaurantPostRequestBody;
import br.com.algaworks.algafoods.requersts.RestaurantPutRequestBody;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class RestaurantMapper {

    public static final RestaurantMapper INSTANCE = Mappers.getMapper(RestaurantMapper.class);

    public abstract Restaurant toRestaurant(RestaurantPostRequestBody restaurantPostRequestBody);
    public abstract Restaurant toRestaurant(RestaurantPutRequestBody restaurantPutRequestBody);
}
