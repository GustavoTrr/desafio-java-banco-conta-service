package com.gustavotorres.cadastroconta.entities;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.gustavotorres.cadastroconta.dtos.ParametroFaixaScoreELimiteDTO;

import org.modelmapper.ModelMapper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "parametros_faixa_score_e_limites")
@Builder
public class ParametroFaixaScoreELimite {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "score", unique = true, nullable = false)
    private Short score;

    @NotNull
    @Column(name = "limite_cartao_em_centavos", nullable = false)
    private BigInteger limiteCartaoEmCentavos;

    @NotNull
    @Column(name = "limite_cheque_especial_em_centavos", nullable = false)
    private BigInteger limiteChequeEspecialEmCentavos;


    public static ParametroFaixaScoreELimite create(ParametroFaixaScoreELimiteDTO parametroFaixaScoreELimiteDTO) {
        return new ModelMapper().map(parametroFaixaScoreELimiteDTO, ParametroFaixaScoreELimite.class);
    }

}
