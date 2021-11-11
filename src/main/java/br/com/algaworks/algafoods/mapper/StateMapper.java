package br.com.algaworks.algafoods.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.algaworks.algafoods.domain.State;
import br.com.algaworks.algafoods.requersts.StatePostRequestBody;
import br.com.algaworks.algafoods.requersts.StatePutRequestBody;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class StateMapper {

    @Autowired
    public static final StateMapper INSTANCE = Mappers.getMapper(StateMapper.class);

    public abstract State toState(StatePostRequestBody statePostRequestBody);
    public abstract State toState(StatePutRequestBody statePutRequestBody);
}
