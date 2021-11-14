package br.com.algaworks.algafoods.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
		return permissionRepository.findById(id).orElseThrow(() -> new BadRequestException("Permission not Found"));
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
			throw new BadRequestException("The Permission cannot be saved");
		} catch (EntityNotFoundException e) {
			throw new BadRequestException("The permission cannot be saved");
		}
	}

	@Transactional
	public void replacePartial(PermissionPutRequestBody permissionPutRequestBody) {
		Permission savedPermission = findByIdOrThrowBadRequestException(permissionPutRequestBody.getId());
		Permission permission = PermissionMapper.INSTANCE.toPermission(permissionPutRequestBody);
		BeanUtils.copyProperties(permission, savedPermission);
		System.out.println(permission);
		System.out.println(savedPermission);
		// permission.setId(savedPermission.getId());

		try {
			permissionRepository.save(savedPermission);
		} catch (EntityNotFoundException | JpaObjectRetrievalFailureException e) {
			throw new BadRequestException("The permission cannot be saved");
		}
	}
	
	@Transactional
	public void replace(PermissionPutRequestBody permissionPutRequestBody) {
		Permission savedPermission = findByIdOrThrowBadRequestException(permissionPutRequestBody.getId());
		Permission permission = PermissionMapper.INSTANCE.toPermission(permissionPutRequestBody);
		permission.setId(savedPermission.getId());
		
		try {
			permissionRepository.save(permission);
		} catch (EntityNotFoundException | JpaObjectRetrievalFailureException e) {
			throw new BadRequestException("The permission cannot be saved");
		}
	}

	public void delete(long id) {
		try {
			permissionRepository.delete(findByIdOrThrowBadRequestException(id));
		} catch (DataIntegrityViolationException e) {
			throw new BadRequestException("The permission cannot be removed. It is in use");
		}
	}

}