package com.gustavotorres.cadastroconta.entities;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.gustavotorres.cadastroconta.dtos.ChequeEspecialDTO;
import com.gustavotorres.cadastroconta.dtos.PessoaDTO;
import com.gustavotorres.cadastroconta.enums.TipoPessoa;

import org.modelmapper.ModelMapper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cheques_especiais")
@Builder
public class ChequeEspecial {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conta_id", nullable = false)
    private Conta conta;

    @NotNull
    @Column(name = "limite_em_centavos", nullable = false)
    private BigInteger limiteEmCentavos;

    public static ChequeEspecial create(ChequeEspecialDTO chequeEspecialDTO) {
        return new ModelMapper().map(chequeEspecialDTO, ChequeEspecial.class);
    }
}
