package br.com.algaworks.algafoods.service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.method.ControllerAdviceBean;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.algaworks.algafoods.AlgafoodsApiApplication;
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

	public City findByIdOrThrowEntityNotFoundException(Long id) {
		return cityRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("City not Found"));
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
		} catch (DataIntegrityViolationException | ConstraintViolationException e) {
			throw new BadRequestException("The City cannot be saved", e.getCause());
		}
	}

	@Transactional
	public City replacePartial(Long id, Map<String, Object> patchRequestBody) {
        City updatedCity = findByIdOrThrowEntityNotFoundException(id);
        
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, Boolean.TRUE);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, Boolean.TRUE);
        
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
		City savedCity = findByIdOrThrowEntityNotFoundException(cityPutRequestBody.getId());
		City city = CityMapper.INSTANCE.toCity(cityPutRequestBody);
		city.setId(savedCity.getId());
		
		try {
			cityRepository.save(city);
		} catch (JpaObjectRetrievalFailureException e) {
			throw new BadRequestException("The city cannot be updated", e.getCause());
		}
	}
	
	public void delete(Long id) {
		try {
			cityRepository.delete(findByIdOrThrowEntityNotFoundException(id));
		} catch (DataIntegrityViolationException e) {
			throw new BadRequestException("The city cannot be removed. It is in use", e.getCause());
		}
	}

}
