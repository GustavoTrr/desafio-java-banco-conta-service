package com.gustavotorres.cadastroconta.dtos;

import java.io.Serializable;
import java.math.BigInteger;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gustavotorres.cadastroconta.entities.ChequeEspecial;

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
public class ChequeEspecialDTO extends RepresentationModel<ChequeEspecialDTO> implements Serializable {
    
    private Long id;

    @NotNull
    @JsonIgnore
    private ContaDTO conta;

    @NotNull
    private BigInteger limiteEmCentavos;

    public static ChequeEspecialDTO create(ChequeEspecial chequeEspecial) {
        return new ModelMapper().map(chequeEspecial, ChequeEspecialDTO.class);
    }
}
