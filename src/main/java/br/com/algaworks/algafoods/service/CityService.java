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

import br.com.algaworks.algafoods.domain.City;
import br.com.algaworks.algafoods.exception.BadRequestException;
import br.com.algaworks.algafoods.mapper.CityMapper;
import br.com.algaworks.algafoods.repository.CityRepository;
import br.com.algaworks.algafoods.requersts.CityPostRequestBody;
import br.com.algaworks.algafoods.requersts.CityPutRequestBody;

@Service
public class CityService {

	@Autowired
	private CityRepository cityRepository;


	public Page<City> listAll(Pageable pageable) {
		return cityRepository.findAll(pageable);
	}

	public List<City> listAllNonPageable() {
		return cityRepository.findAll();
	}

	public City findByIdOrThrowBadRequestException(Long id) {
		return cityRepository.findById(id).orElseThrow(() -> new BadRequestException("City not Found"));
	}
	
	public List<City> findByName(String name) {
		return cityRepository.findByName(name);
	}

	public List<City> findByNameContaining(String name) {
		return cityRepository.findByNameContaining(name);
	}

	public List<City> findByNameContainingAndStateNameContaining(String cityName, String stateName) {
		return cityRepository.findByNameContainingAndStateNameContaining(cityName, stateName);
	}

	public List<City> findByNameContainingAndStateId(String name, Long stateId) {
		return cityRepository.findByNameContainingAndStateId(name, stateId);
	}

	@Transactional
	public City save(CityPostRequestBody cityPostRequestBody) {
		try {
			return cityRepository.save(CityMapper.INSTANCE.toCity(cityPostRequestBody));
		} catch (DataIntegrityViolationException | EntityNotFoundException e) {
			throw new BadRequestException("The City cannot be saved");
		}
	}

	@Transactional
	public City replacePartial(Long id, Map<String, Object> patchRequestBody) {
        City updatedCity = findByIdOrThrowBadRequestException(id);
        
        ObjectMapper objectMapper = new ObjectMapper();
        City city = objectMapper.convertValue(patchRequestBody, City.class);
        
        patchRequestBody.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(City.class, key);
            field.setAccessible(Boolean.TRUE);
            Object newValue = ReflectionUtils.getField(field, city);
            ReflectionUtils.setField(field, updatedCity, newValue);
        });
        
        return cityRepository.save(updatedCity);
	}

	@Transactional
	public void replace(CityPutRequestBody cityPutRequestBody) {
		City savedCity = findByIdOrThrowBadRequestException(cityPutRequestBody.getId());
		City city = CityMapper.INSTANCE.toCity(cityPutRequestBody);
		city.setId(savedCity.getId());
		
		try {
			cityRepository.save(city);
		} catch (EntityNotFoundException | JpaObjectRetrievalFailureException e) {
			throw new BadRequestException("The city cannot be updated");
		}
	}
	
	public void delete(Long id) {
		try {
			cityRepository.delete(findByIdOrThrowBadRequestException(id));
		} catch (DataIntegrityViolationException e) {
			throw new BadRequestException("The city cannot be removed. It is in use");
		}
	}

}
