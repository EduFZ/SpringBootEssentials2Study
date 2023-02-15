package br.com.devdojo.springboot2.client;

import br.com.devdojo.springboot2.domain.Anime;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Log4j2
public class SpringClient {
    public static void main(String[] args) {
        ResponseEntity<Anime> entity = new RestTemplate().getForEntity("http://localhost:8080/animes/{id}", Anime.class, 7);
        log.info(entity);

        Anime object = new RestTemplate().getForObject("http://localhost:8080/animes/{id}", Anime.class, 7);
        log.info(object);

        Anime[] animes = new RestTemplate().getForObject("http://localhost:8080/animes/all", Anime[].class);

        log.info(Arrays.toString(animes));

        ResponseEntity<List<Anime>> exchange = new RestTemplate().exchange("http://localhost:8080/animes/all",
                HttpMethod.GET, null,
                new ParameterizedTypeReference<>() {
                });

        log.info(exchange.getBody());

//        Anime kingdom = Anime.builder().name("kingdom").build();
//        Anime kingdomSaved =  new RestTemplate().postForObject("http://localhost:8080/animes/", kingdom, Anime.class);
//        log.info("Saved anime {}", kingdomSaved);

        Anime theMandalorian = Anime.builder().name("The Mandalorian").build();
        ResponseEntity<Anime> theMandalorianSaved = new RestTemplate().exchange("http://localhost:8080/animes/",
                HttpMethod.POST,
                new HttpEntity<>(theMandalorian, createJsonHeader()),
                Anime.class);
        log.info("Saved anime {}", theMandalorianSaved);

        Anime animeToBeUpdated = theMandalorianSaved.getBody();
        animeToBeUpdated.setName("The Mandalorian 2");

        ResponseEntity<Void> theMandalorianUpdate = new RestTemplate().exchange("http://localhost:8080/animes/",
                HttpMethod.PUT,
                new HttpEntity<>(animeToBeUpdated, createJsonHeader()),
                Void.class);

        log.info(theMandalorianUpdate);

        ResponseEntity<Void> theMandalorianDelete = new RestTemplate().exchange("http://localhost:8080/animes/{id}",
                HttpMethod.DELETE,
                null,
                Void.class,
                animeToBeUpdated.getId());

        log.info(theMandalorianDelete);
    }

    private static HttpHeaders createJsonHeader(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;
    }
}
