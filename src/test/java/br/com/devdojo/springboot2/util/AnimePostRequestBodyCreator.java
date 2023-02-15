package br.com.devdojo.springboot2.util;

import br.com.devdojo.springboot2.domain.Anime;
import br.com.devdojo.springboot2.request.AnimePostRequestBody;

public class AnimePostRequestBodyCreator {

    public static AnimePostRequestBody createAnimePostRequestBody(){
        return AnimePostRequestBody.builder()
                .name(AnimeCreator.createAnimeToBeSaved().getName())
                .build();
    }
}
