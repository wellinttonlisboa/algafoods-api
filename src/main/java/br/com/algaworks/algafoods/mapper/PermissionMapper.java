package br.com.algaworks.algafoods.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import br.com.algaworks.algafoods.domain.Permission;
import br.com.algaworks.algafoods.requersts.PermissionPostRequestBody;
import br.com.algaworks.algafoods.requersts.PermissionPutRequestBody;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class PermissionMapper {

    public static final PermissionMapper INSTANCE = Mappers.getMapper(PermissionMapper.class);

    public abstract Permission toPermission(PermissionPostRequestBody permissionPostRequestBody);
    public abstract Permission toPermission(PermissionPutRequestBody permissionPutRequestBody);
}
