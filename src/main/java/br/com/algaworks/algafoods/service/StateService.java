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

	public State findByIdOrThrowBadRequestException(long id) {
		return stateRepository.findById(id).orElseThrow(() -> new BadRequestException("State not Found"));
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
			throw new BadRequestException("The State cannot be saved");
		}
	}

	@Transactional
	public void replacePartial(StatePutRequestBody statePutRequestBody) {
		State savedState = findByIdOrThrowBadRequestException(statePutRequestBody.getId());
		State state = StateMapper.INSTANCE.toState(statePutRequestBody);
		BeanUtils.copyProperties(state, savedState);
	    System.out.println(state);
	    System.out.println(savedState);
		//state.setId(savedState.getId());

		try {
			stateRepository.save(savedState);
		} catch (EntityNotFoundException | JpaObjectRetrievalFailureException e) {
			throw new BadRequestException("The state cannot be saved");
		}
	}
	
	@Transactional
	public void replace(StatePutRequestBody statePutRequestBody) {
		State savedState = findByIdOrThrowBadRequestException(statePutRequestBody.getId());
		State state = StateMapper.INSTANCE.toState(statePutRequestBody);
		state.setId(savedState.getId());
		
		try {
			stateRepository.save(state);
		} catch (EntityNotFoundException | JpaObjectRetrievalFailureException e) {
			throw new BadRequestException("The state cannot be saved");
		}
	}
	
	public void delete(long id) {
		try {
			stateRepository.delete(findByIdOrThrowBadRequestException(id));
		} catch (DataIntegrityViolationException e) {
			throw new BadRequestException("The state cannot be removed it is in use");
		}
	}

}