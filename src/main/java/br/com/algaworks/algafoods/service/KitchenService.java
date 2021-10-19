package br.com.algaworks.algafoods.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.com.algaworks.algafoods.domain.Kitchen;
import br.com.algaworks.algafoods.repository.KitchenRepository;

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

    public Kitchen findById(long id) {
        return kitchenRepository.findById(id).get();
    }
    
    //    public Kitchen findByIdOrThrowBadRequestException(long id) {
    //        return kitchenRepository.findById(id)
    //                .orElseThrow(() -> new BadRequestException("Anime not Found"));
    //    }
    
    @Transactional
    public Kitchen save(Kitchen kitchen) {
        return kitchenRepository.save(kitchen);
    }

    //    @Transactional
    //    public Kitchen save(AnimePostRequestBody animePostRequestBody) {
    //        return animeRepository.save(AnimeMapper.INSTANCE.toAnime(animePostRequestBody));
    //    }

    //    public void delete(long id) {
    //        kitchenRepository.delete(findByIdOrThrowBadRequestException(id));
    //    }

    //    public void replace(AnimePutRequestBody animePutRequestBody) {
    //        Kitchen savedAnime = findByIdOrThrowBadRequestException(animePutRequestBody.getId());
    //        Kitchen anime = AnimeMapper.INSTANCE.toAnime(animePutRequestBody);
    //        anime.setId(savedAnime.getId());
    //        animeRepository.save(anime);
    //    }
}
