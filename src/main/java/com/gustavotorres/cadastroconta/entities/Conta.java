package com.gustavotorres.cadastroconta.entities;

import java.util.List;

import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.gustavotorres.cadastroconta.dtos.ContaDTO;
import com.gustavotorres.cadastroconta.dtos.PessoaDTO;
import com.gustavotorres.cadastroconta.enums.TipoConta;

import org.modelmapper.ModelMapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "contas")
public class Conta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_conta", unique = true)
    private String numeroConta;

    @Column(name = "agencia")
    private String agencia;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_conta", length = 1, nullable = false)
    private TipoConta tipoConta;

    @NotNull
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pessoa_id", nullable = false)
    private Pessoa pessoa;

    // @JsonManagedReference
    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "conta", cascade = CascadeType.REFRESH)
    private ChequeEspecial chequeEspecial;

    // @JsonManagedReference
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "conta", cascade = CascadeType.REFRESH)
    private List<CartaoDeCredito> cartoes;

    public static Conta create(ContaDTO contaDTO) {
        return new ModelMapper().map(contaDTO, Conta.class);
    }
}
