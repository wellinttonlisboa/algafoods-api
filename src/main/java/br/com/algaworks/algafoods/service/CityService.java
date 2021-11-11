package br.com.algaworks.algafoods.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.mapstruct.BeanMapping;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.algaworks.algafoods.domain.City;
import br.com.algaworks.algafoods.exception.BadRequestException;
import br.com.algaworks.algafoods.mapper.CityMapper;
import br.com.algaworks.algafoods.repository.CityRepository;
import br.com.algaworks.algafoods.requersts.CityPostRequestBody;
import br.com.algaworks.algafoods.requersts.CityPutRequestBody;
import ch.qos.logback.core.joran.util.beans.BeanUtil;

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

	public List<City> findByName(String name) {
		return cityRepository.findByName(name);
	}

	public City findByIdOrThrowBadRequestException(long id) {
		return cityRepository.findById(id).orElseThrow(() -> new BadRequestException("City not Found"));
	}

	@Transactional
	public City save(CityPostRequestBody cityPostRequestBody) {
		try {
			return cityRepository.save(CityMapper.INSTANCE.toCity(cityPostRequestBody));
		} catch (DataIntegrityViolationException e) {
			throw new BadRequestException("The City cannot be saved");
		} catch (EntityNotFoundException e) {
			throw new BadRequestException("The city cannot be saved");
		}
	}

	@Transactional
	public void replace(CityPutRequestBody cityPutRequestBody) {
		City savedCity = findByIdOrThrowBadRequestException(cityPutRequestBody.getId());
		City city = CityMapper.INSTANCE.toCity(cityPutRequestBody);
		city.setId(savedCity.getId());

		try {
			cityRepository.save(city);
		} catch (EntityNotFoundException | JpaObjectRetrievalFailureException e) {
			throw new BadRequestException("The city cannot be saved");
		}
	}

	@Transactional
	public void replacePartial(CityPutRequestBody cityPutRequestBody) {
		City savedCity = findByIdOrThrowBadRequestException(cityPutRequestBody.getId());
		City city = CityMapper.INSTANCE.toCity(cityPutRequestBody);
		BeanUtils.copyProperties(city, savedCity);
	    System.out.println(city);
	    System.out.println(savedCity);
		//city.setId(savedCity.getId());

		try {
			cityRepository.save(savedCity);
		} catch (EntityNotFoundException | JpaObjectRetrievalFailureException e) {
			throw new BadRequestException("The city cannot be saved");
		}
	}
	
	public void delete(long id) {
		try {
			cityRepository.delete(findByIdOrThrowBadRequestException(id));
		} catch (DataIntegrityViolationException e) {
			throw new BadRequestException("The city cannot be removed it is in use");
		}
	}

}
