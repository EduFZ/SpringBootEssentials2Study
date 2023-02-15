package br.com.devdojo.springboot2.repository;

import br.com.devdojo.springboot2.domain.Anime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnimeRepository extends JpaRepository<Anime, Long> { //AnimeRepository será a conexão diretamente com o Banco de Dados. Listar, Procurar, etc ficarão todas nessa mesma interface;

    List<Anime> findByName(String name);
}
