package com.gustavotorres.cadastroconta.dtos;

import java.io.Serializable;
import java.util.List;

import com.gustavotorres.cadastroconta.entities.Conta;
import com.gustavotorres.cadastroconta.enums.TipoConta;

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
public class ContaDTO extends RepresentationModel<ContaDTO> implements Serializable {
    
    private Long id;

    private Short numeroConta;

    private String agencia;

    private TipoConta tipoConta;

    private PessoaDTO pessoa;

    private ChequeEspecialDTO chequeEspecial;

    private List<CartaoDeCreditoDTO> cartaoDeCredito;

    public static ContaDTO create(Conta conta) {
        return new ModelMapper().map(conta, ContaDTO.class);
    }
}
