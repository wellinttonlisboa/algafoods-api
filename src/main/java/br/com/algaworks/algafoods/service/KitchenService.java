package br.com.algaworks.algafoods.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        return kitchenRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Kitchen not Found"));
    }

    @Transactional
    public Kitchen save(KitchenPostRequestBody kitchenPostRequestBody) {
        return kitchenRepository.save(KitchenMapper.INSTANCE.toKitchen(kitchenPostRequestBody));
    }

    @Transactional
    public void replace(KitchenPutRequestBody kitchenPutRequestBody) {
        Kitchen savedKitchen = findByIdOrThrowBadRequestException(kitchenPutRequestBody.getId());
        Kitchen kitchen = KitchenMapper.INSTANCE.toKitchen(kitchenPutRequestBody);
        kitchen.setId(savedKitchen.getId());
        kitchenRepository.save(kitchen);
    }

    public void delete(long id) {
    	kitchenRepository.delete(findByIdOrThrowBadRequestException(id));
    }

}
