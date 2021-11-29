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

import br.com.algaworks.algafoods.domain.State;
import br.com.algaworks.algafoods.exception.BadRequestException;
import br.com.algaworks.algafoods.mapper.StateMapper;
import br.com.algaworks.algafoods.repository.StateRepository;
import br.com.algaworks.algafoods.requersts.StatePostRequestBody;
import br.com.algaworks.algafoods.requersts.StatePutRequestBody;

@Service
public class StateService {

	@Autowired
	private StateRepository stateRepository;

	public Page<State> listAll(Pageable pageable) {
		return stateRepository.findAll(pageable);
	}

	public List<State> listAllNonPageable() {
		return stateRepository.findAll();
	}

	public State findByIdOrThrowBadRequestException(Long id) {
		return stateRepository.findById(id).orElseThrow(() -> new BadRequestException("State not Found", null));
	}
	
	public List<State> findByName(String name) {
		return stateRepository.findByName(name);
	}
	
	public List<State> findByNameContaining(String name) {
		return stateRepository.findByNameContaining(name);
	}
	
	@Transactional
	public State save(StatePostRequestBody statePostRequestBody) {
		try {
			return stateRepository.save(StateMapper.INSTANCE.toState(statePostRequestBody));
		} catch (DataIntegrityViolationException | EntityNotFoundException e) {
			throw new BadRequestException("The State cannot be saved", null);
		}
	}

	@Transactional
	public State replacePartial(Long id, Map<String, Object> patchRequestBody) {
        State updatedState = findByIdOrThrowBadRequestException(id);
        
        ObjectMapper objectMapper = new ObjectMapper();
        State state = objectMapper.convertValue(patchRequestBody, State.class);
        
        patchRequestBody.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(State.class, key);
            field.setAccessible(Boolean.TRUE);
            Object newValue = ReflectionUtils.getField(field, state);
            ReflectionUtils.setField(field, updatedState, newValue);
        });
        
        return stateRepository.save(updatedState);
	}
	
	@Transactional
	public void replace(StatePutRequestBody statePutRequestBody) {
		State savedState = findByIdOrThrowBadRequestException(statePutRequestBody.getId());
		State state = StateMapper.INSTANCE.toState(statePutRequestBody);
		state.setId(savedState.getId());
		
		try {
			stateRepository.save(state);
		} catch (EntityNotFoundException | JpaObjectRetrievalFailureException e) {
			throw new BadRequestException("The state cannot be saved", null);
		}
	}
	
	public void delete(Long id) {
		try {
			stateRepository.delete(findByIdOrThrowBadRequestException(id));
		} catch (DataIntegrityViolationException e) {
			throw new BadRequestException("The state cannot be removed it is in use", null);
		}
	}

}