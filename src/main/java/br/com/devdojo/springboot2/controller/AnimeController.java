package br.com.devdojo.springboot2.controller;

import br.com.devdojo.springboot2.domain.Anime;
import br.com.devdojo.springboot2.request.AnimePostRequestBody;
import br.com.devdojo.springboot2.request.AnimePutRequestBody;
import br.com.devdojo.springboot2.service.AnimeService;
import br.com.devdojo.springboot2.util.DateUtil;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("animes")
@Log4j2
@AllArgsConstructor //Anotação do lombok para criar todos os construtores da classe


public class AnimeController { // Classe será simples onde terá somente os endpoints;
//    private final DateUtil dateUtil;
    private final AnimeService animeService;

    @GetMapping
    public ResponseEntity<Page<Anime>> list(Pageable pageable){
//        log.info(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return ResponseEntity.ok(animeService.listAll(pageable)); //Boa prática utilizar ResponseEntity. Necessário declarar na assinatura do método antes de utilizá-lo;
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<Anime>> listAll(){
//        log.info(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return ResponseEntity.ok(animeService.listAllNonPageable()); //Boa prática utilizar ResponseEntity. Necessário declarar na assinatura do método antes de utilizá-lo;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Anime> findById(@PathVariable long id){
        return ResponseEntity.ok(animeService.findByIdOrThrowBadRequestException(id)); //Boa prática utilizar ResponseEntity. Necessário declarar na assinatura do método antes de utilizá-lo;
    }
    @GetMapping(path = "/find/{name}")
    public ResponseEntity<List<Anime>> findByName(@RequestParam String name){
        return ResponseEntity.ok(animeService.findByName(name)); //Boa prática utilizar ResponseEntity. Necessário declarar na assinatura do método antes de utilizá-lo;
    }

    @PostMapping
    public ResponseEntity<Anime> save(@RequestBody @Valid AnimePostRequestBody animePostRequestBody) {
        return new ResponseEntity<>(animeService.save(animePostRequestBody), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        animeService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody AnimePutRequestBody animePutRequestBody){
        animeService.replace(animePutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
