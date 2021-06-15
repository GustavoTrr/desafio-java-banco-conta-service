package com.gustavotorres.cadastroconta.dtos;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gustavotorres.cadastroconta.entities.Pessoa;

import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class PessoaDTO extends RepresentationModel<PessoaDTO> implements Serializable {
    
    private Long id;

    private String idPublico;

    @JsonIgnore
    private ContaDTO conta;

    public static PessoaDTO create(Pessoa pessoa) {
        return new ModelMapper().map(pessoa, PessoaDTO.class);
    }
}
