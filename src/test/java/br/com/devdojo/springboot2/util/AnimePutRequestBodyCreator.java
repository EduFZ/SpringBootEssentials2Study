package br.com.devdojo.springboot2.util;

import br.com.devdojo.springboot2.request.AnimePostRequestBody;
import br.com.devdojo.springboot2.request.AnimePutRequestBody;

public class AnimePutRequestBodyCreator {

    public static AnimePutRequestBody createAnimePutRequestBody(){
        return AnimePutRequestBody.builder()
                .id(AnimeCreator.createValidUpdatedAnime().getId())
                .name(AnimeCreator.createValidUpdatedAnime().getName())
                .build();
    }
}
