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

import br.com.algaworks.algafoods.domain.PaymentMethod;
import br.com.algaworks.algafoods.exception.BadRequestException;
import br.com.algaworks.algafoods.mapper.PaymentMethodMapper;
import br.com.algaworks.algafoods.repository.PaymentMethodRepository;
import br.com.algaworks.algafoods.requersts.PaymentMethodPostRequestBody;
import br.com.algaworks.algafoods.requersts.PaymentMethodPutRequestBody;
import ch.qos.logback.core.joran.util.beans.BeanUtil;

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

	public List<PaymentMethod> findByName(String name) {
		return paymentMethodRepository.findByName(name);
	}

	public PaymentMethod findByIdOrThrowBadRequestException(long id) {
		return paymentMethodRepository.findById(id).orElseThrow(() -> new BadRequestException("PaymentMethod not Found"));
	}

	@Transactional
	public PaymentMethod save(PaymentMethodPostRequestBody paymentMethodPostRequestBody) {
		try {
			return paymentMethodRepository.save(PaymentMethodMapper.INSTANCE.toPaymentMethod(paymentMethodPostRequestBody));
		} catch (DataIntegrityViolationException e) {
			throw new BadRequestException("The payment method cannot be saved");
		} catch (EntityNotFoundException e) {
			throw new BadRequestException("The payment method cannot be saved");
		}
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

	@Transactional
	public void replacePartial(PaymentMethodPutRequestBody paymentMethodPutRequestBody) {
		PaymentMethod savedPaymentMethod = findByIdOrThrowBadRequestException(paymentMethodPutRequestBody.getId());
		PaymentMethod paymentMethod = PaymentMethodMapper.INSTANCE.toPaymentMethod(paymentMethodPutRequestBody);
		BeanUtils.copyProperties(paymentMethod, savedPaymentMethod);
	    System.out.println(paymentMethod);
	    System.out.println(savedPaymentMethod);
		//paymentMethod.setId(savedPaymentMethod.getId());

		try {
			paymentMethodRepository.save(savedPaymentMethod);
		} catch (EntityNotFoundException | JpaObjectRetrievalFailureException e) {
			throw new BadRequestException("The payment method cannot be saved");
		}
	}
	
	public void delete(long id) {
		try {
			paymentMethodRepository.delete(findByIdOrThrowBadRequestException(id));
		} catch (DataIntegrityViolationException e) {
			throw new BadRequestException("The payment method cannot be removed it is in use");
		}
	}

}
