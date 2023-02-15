package br.com.devdojo.springboot2.util;

import br.com.devdojo.springboot2.domain.Anime;

public class AnimeCreator {

    public static Anime createAnimeToBeSaved(){
        return Anime.builder()
                .name("Star Wars")
                .build();
    }

    public static Anime createValidAnime(){
        return Anime.builder()
                .name("Star Wars")
                .id(1L)
                .build();
    }

    public static Anime createValidUpdatedAnime(){
        return Anime.builder()
                .name("Star Wars IV")
                .id(1L)
                .build();
    }

}
