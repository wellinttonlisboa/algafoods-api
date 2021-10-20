package br.com.algaworks.algafoods.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import br.com.algaworks.algafoods.domain.Kitchen;
import br.com.algaworks.algafoods.requersts.KitchenPostRequestBody;
import br.com.algaworks.algafoods.requersts.KitchenPutRequestBody;

@Mapper(componentModel = "spring")
public abstract class KitchenMapper {

    public static final KitchenMapper INSTANCE = Mappers.getMapper(KitchenMapper.class);

    public abstract Kitchen toKitchen(KitchenPostRequestBody kitchenPostRequestBody);

    public abstract Kitchen toKitchen(KitchenPutRequestBody kitchenPostRequestBody);
}
