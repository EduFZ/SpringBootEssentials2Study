package br.com.devdojo.springboot2.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Anime { // Classe que representa o que temos do Banco de Dados
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@JsonProperty("nome") //caso o nome do atributo seja diferente do Json, utilizar JsonProperty("name") sinalizando que o atributo é o mesmo que o respectivo Json
    @NotNull
    @NotEmpty(message = "The anime name cannot be empty")
    private String name;

}

