package br.com.algaworks.algafoods.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.algaworks.algafoods.domain.City;
import br.com.algaworks.algafoods.requersts.CityPostRequestBody;
import br.com.algaworks.algafoods.requersts.CityPutRequestBody;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class CityMapper {

    @Autowired
    public static final CityMapper INSTANCE = Mappers.getMapper(CityMapper.class);

    public abstract City toCity(CityPostRequestBody cityPostRequestBody);
    public abstract City toCity(CityPutRequestBody cityPutRequestBody);
}
