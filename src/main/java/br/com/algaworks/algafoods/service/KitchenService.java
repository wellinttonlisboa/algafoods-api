package br.com.algaworks.algafoods.service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import br.com.algaworks.algafoods.domain.Kitchen;
import br.com.algaworks.algafoods.exception.BadRequestException;
import br.com.algaworks.algafoods.mapper.KitchenMapper;
import br.com.algaworks.algafoods.repository.KitchenRepository;
import br.com.algaworks.algafoods.requersts.KitchenPostRequestBody;
import br.com.algaworks.algafoods.requersts.KitchenPutRequestBody;

@Service
public class KitchenService {

    @Autowired
    private KitchenRepository kitchenRepository;

    public Page<Kitchen> listAll(Pageable pageable) {
        return kitchenRepository.findAll(pageable);
    }

    public List<Kitchen> listAllNonPageable() {
        return kitchenRepository.findAll();
    }

    public List<Kitchen> findByName(String name) {
        return kitchenRepository.findByName(name);
    }

    public Kitchen findByIdOrThrowBadRequestException(long id) {
        return kitchenRepository.findById(id).orElseThrow(() -> new BadRequestException("Kitchen not Found"));
    }

    @Transactional
    public Kitchen save(KitchenPostRequestBody kitchenPostRequestBody) {
        return kitchenRepository.save(KitchenMapper.INSTANCE.toKitchen(kitchenPostRequestBody));
    }

    @Transactional
    public Kitchen save(Long id, Map<String, Object> patchRequestBody) {
        Kitchen savedKitchen = findByIdOrThrowBadRequestException(id);
       
        ObjectMapper objectMapper = new ObjectMapper();
        Kitchen updateKitchen = objectMapper.convertValue(patchRequestBody, Kitchen.class);
        
        patchRequestBody.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Kitchen.class, key);
            field.setAccessible(Boolean.TRUE);
            Object newValue = ReflectionUtils.getField(field, savedKitchen);
            ReflectionUtils.setField(field, updateKitchen, newValue);
        });
        
        return kitchenRepository.save(updateKitchen);
    }

    @Transactional
    public void replace(KitchenPutRequestBody kitchenPutRequestBody) {
        Kitchen savedKitchen = findByIdOrThrowBadRequestException(kitchenPutRequestBody.getId());
        Kitchen kitchen = KitchenMapper.INSTANCE.toKitchen(kitchenPutRequestBody);
        kitchen.setId(savedKitchen.getId());
        kitchenRepository.save(kitchen);
    }

    public void delete(long id) {
        try {
            kitchenRepository.delete(findByIdOrThrowBadRequestException(id));
        } catch (DataIntegrityViolationException e) {
            throw new BadRequestException("The Kitchen cannot be removed it is in use");
        }
    }

}
