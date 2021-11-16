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

import br.com.algaworks.algafoods.domain.PaymentMethod;
import br.com.algaworks.algafoods.exception.BadRequestException;
import br.com.algaworks.algafoods.mapper.PaymentMethodMapper;
import br.com.algaworks.algafoods.repository.PaymentMethodRepository;
import br.com.algaworks.algafoods.requersts.PaymentMethodPostRequestBody;
import br.com.algaworks.algafoods.requersts.PaymentMethodPutRequestBody;

@Service
public class PaymentMethodService {

	@Autowired
	private PaymentMethodRepository paymentMethodRepository;

	public Page<PaymentMethod> listAll(Pageable pageable) {
		return paymentMethodRepository.findAll(pageable);
	}

	public List<PaymentMethod> listAllNonPageable() {
		return paymentMethodRepository.findAll();
	}

	public PaymentMethod findByIdOrThrowBadRequestException(Long id) {
		return paymentMethodRepository.findById(id)
				.orElseThrow(() -> new BadRequestException("PaymentMethod not Found"));
	}

	public List<PaymentMethod> findByName(String name) {
		return paymentMethodRepository.findByName(name);
	}

	public List<PaymentMethod> findByNameContaining(String name) {
		return paymentMethodRepository.findByNameContaining(name);
	}

	@Transactional
	public PaymentMethod save(PaymentMethodPostRequestBody paymentMethodPostRequestBody) {
		try {
			return paymentMethodRepository
					.save(PaymentMethodMapper.INSTANCE.toPaymentMethod(paymentMethodPostRequestBody));
		} catch (DataIntegrityViolationException | EntityNotFoundException e) {
			throw new BadRequestException("The payment method cannot be saved");
		}
	}

	@Transactional
	public PaymentMethod replacePartial(Long id, Map<String, Object> patchRequestBody) {
        PaymentMethod updatedPaymentMethod = findByIdOrThrowBadRequestException(id);
        
        ObjectMapper objectMapper = new ObjectMapper();
        PaymentMethod paymentMethod = objectMapper.convertValue(patchRequestBody, PaymentMethod.class);
        
        patchRequestBody.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(PaymentMethod.class, key);
            field.setAccessible(Boolean.TRUE);
            Object newValue = ReflectionUtils.getField(field, paymentMethod);
            ReflectionUtils.setField(field, updatedPaymentMethod, newValue);
        });
        
        return paymentMethodRepository.save(updatedPaymentMethod);
	}

	@Transactional
	public void replace(PaymentMethodPutRequestBody paymentMethodPutRequestBody) {
		PaymentMethod savedPaymentMethod = findByIdOrThrowBadRequestException(paymentMethodPutRequestBody.getId());
		PaymentMethod paymentMethod = PaymentMethodMapper.INSTANCE.toPaymentMethod(paymentMethodPutRequestBody);
		paymentMethod.setId(savedPaymentMethod.getId());
		
		try {
			paymentMethodRepository.save(paymentMethod);
		} catch (EntityNotFoundException | JpaObjectRetrievalFailureException e) {
			throw new BadRequestException("The payment method cannot be saved");
		}
	}
	
	public void delete(Long id) {
		try {
			paymentMethodRepository.delete(findByIdOrThrowBadRequestException(id));
		} catch (DataIntegrityViolationException e) {
			throw new BadRequestException("The payment method cannot be removed. It is in use");
		}
	}

}