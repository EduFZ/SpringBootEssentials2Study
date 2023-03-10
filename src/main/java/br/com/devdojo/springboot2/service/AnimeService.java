package br.com.devdojo.springboot2.service;

import br.com.devdojo.springboot2.domain.Anime;
import br.com.devdojo.springboot2.exception.BadRequestException;
import br.com.devdojo.springboot2.mapper.AnimeMapper;
import br.com.devdojo.springboot2.repository.AnimeRepository;
import br.com.devdojo.springboot2.request.AnimePostRequestBody;
import br.com.devdojo.springboot2.request.AnimePutRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


//@Service
@Repository
@RequiredArgsConstructor
public class AnimeService { // Onde terá a lógica de negócios;

    private final AnimeRepository animeRepository;
    public Page<Anime> listAll(Pageable pageable){
        return animeRepository.findAll(pageable);
    }

    public List<Anime> listAllNonPageable() {
        return animeRepository.findAll();
    }

    public List<Anime> findByName(String name){
        return animeRepository.findByName(name);
    }
    public Anime findByIdOrThrowBadRequestException(long id){
        return animeRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Anime not Found")); //.orElseThrow serve para, caso o id retornado na url não exista, que avise o usuário retornando a String "Anime not Found";
    }

    @Transactional
    public Anime save(AnimePostRequestBody animePostRequestBody) {
        return animeRepository.save(AnimeMapper.INSTANCE.toAnime(animePostRequestBody));
    }

    public void delete(long id) {
        animeRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    public void replace(AnimePutRequestBody animePutRequestBody) {
        Anime savedAnime = findByIdOrThrowBadRequestException(animePutRequestBody.getId());
        Anime anime = AnimeMapper.INSTANCE.toAnime(animePutRequestBody);
        anime.setId(savedAnime.getId());
        animeRepository.save(anime);
    }


}
