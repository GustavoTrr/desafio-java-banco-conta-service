package com.gustavotorres.cadastroconta.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.gustavotorres.cadastroconta.dtos.PessoaCadastroInputDTO;
import com.gustavotorres.cadastroconta.dtos.PessoaDTO;

import org.modelmapper.ModelMapper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pessoas")
@Builder
public class Pessoa {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "id_publico", unique = true, nullable = false)
    private String idPublico;

    @JsonManagedReference
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "pessoa", cascade = CascadeType.REFRESH)
    private Conta conta;

    public static Pessoa create(PessoaDTO pessoaDTO) {
        return new ModelMapper().map(pessoaDTO, Pessoa.class);
    }

    public static Pessoa create(PessoaCadastroInputDTO pessoaCadastroDTO) {
        return Pessoa.builder()
            .idPublico(pessoaCadastroDTO.getIdPublico())
            .build();
    }
}
