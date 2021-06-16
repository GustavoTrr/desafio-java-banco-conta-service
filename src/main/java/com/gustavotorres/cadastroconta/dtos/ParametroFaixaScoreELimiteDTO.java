package com.gustavotorres.cadastroconta.dtos;

import java.io.Serializable;
import java.math.BigInteger;

import com.gustavotorres.cadastroconta.entities.ParametroFaixaScoreELimite;

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
public class ParametroFaixaScoreELimiteDTO extends RepresentationModel<ParametroFaixaScoreELimiteDTO> implements Serializable {
    
    private Long id;

    private Short score;

    private BigInteger limiteCartaoEmCentavos;

    private BigInteger limiteChequeEspecialEmCentavos;

    public static ParametroFaixaScoreELimiteDTO create(ParametroFaixaScoreELimite parametroFaixaScoreELimite) {
        return new ModelMapper().map(parametroFaixaScoreELimite, ParametroFaixaScoreELimiteDTO.class);
    }
}
