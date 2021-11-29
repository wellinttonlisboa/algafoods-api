package br.com.algaworks.algafoods.service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

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

import br.com.algaworks.algafoods.domain.Permission;
import br.com.algaworks.algafoods.exception.BadRequestException;
import br.com.algaworks.algafoods.mapper.PermissionMapper;
import br.com.algaworks.algafoods.repository.PermissionRepository;
import br.com.algaworks.algafoods.requersts.PermissionPostRequestBody;
import br.com.algaworks.algafoods.requersts.PermissionPutRequestBody;

@Service
public class PermissionService {

	@Autowired
	private PermissionRepository permissionRepository;

	public Page<Permission> listAll(Pageable pageable) {
		return permissionRepository.findAll(pageable);
	}

	public List<Permission> listAllNonPageable() {
		return permissionRepository.findAll();
	}

	public Permission findByIdOrThrowBadRequestException(Long id) {
		return permissionRepository.findById(id).orElseThrow(() -> new BadRequestException("Permission not Found", null));
	}

	public List<Permission> findByName(String name) {
		return permissionRepository.findByName(name);
	}

	public List<Permission> findByNameContaining(String name) {
		return permissionRepository.findByNameContaining(name);
	}

	@Transactional
	public Permission save(PermissionPostRequestBody permissionPostRequestBody) {
		try {
			return permissionRepository.save(PermissionMapper.INSTANCE.toPermission(permissionPostRequestBody));
		} catch (DataIntegrityViolationException e) {
			throw new BadRequestException("The Permission cannot be saved", null);
		} catch (EntityNotFoundException e) {
			throw new BadRequestException("The permission cannot be saved", null);
		}
	}

	@Transactional
	public Permission replacePartial(Long id, Map<String, Object> patchRequestBody) {
        Permission updatedPermission = findByIdOrThrowBadRequestException(id);
        
        ObjectMapper objectMapper = new ObjectMapper();
        Permission permission = objectMapper.convertValue(patchRequestBody, Permission.class);
        
        patchRequestBody.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Permission.class, key);
            field.setAccessible(Boolean.TRUE);
            Object newValue = ReflectionUtils.getField(field, permission);
            ReflectionUtils.setField(field, updatedPermission, newValue);
        });
        
        return permissionRepository.save(updatedPermission);
	}
	
	@Transactional
	public void replace(PermissionPutRequestBody permissionPutRequestBody) {
		Permission savedPermission = findByIdOrThrowBadRequestException(permissionPutRequestBody.getId());
		Permission permission = PermissionMapper.INSTANCE.toPermission(permissionPutRequestBody);
		permission.setId(savedPermission.getId());
		
		try {
			permissionRepository.save(permission);
		} catch (EntityNotFoundException | JpaObjectRetrievalFailureException e) {
			throw new BadRequestException("The permission cannot be saved", null);
		}
	}

	public void delete(Long id) {
		try {
			permissionRepository.delete(findByIdOrThrowBadRequestException(id));
		} catch (DataIntegrityViolationException e) {
			throw new BadRequestException("The permission cannot be removed. It is in use", null);
		}
	}

}