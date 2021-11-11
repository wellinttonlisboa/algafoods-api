package br.com.algaworks.algafoods.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.algaworks.algafoods.domain.Kitchen;
import br.com.algaworks.algafoods.requersts.KitchenPostRequestBody;
import br.com.algaworks.algafoods.requersts.KitchenPutRequestBody;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class KitchenMapper {

    @Autowired
    public static final KitchenMapper INSTANCE = Mappers.getMapper(KitchenMapper.class);

    public abstract Kitchen toKitchen(KitchenPostRequestBody kitchenPostRequestBody);
    public abstract Kitchen toKitchen(KitchenPutRequestBody kitchenPostRequestBody);
}
