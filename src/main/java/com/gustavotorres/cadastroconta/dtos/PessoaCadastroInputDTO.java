package com.gustavotorres.cadastroconta.dtos;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PessoaCadastroInputDTO {

    @NotEmpty
    private String idPublico;

    @Max(9)
    @Min(0)
    @NotNull
    private Short score;

    @Enumerated(EnumType.STRING)
    @NotEmpty
    @Size(min = 2, max = 2)
    private String tipoPessoa;

}
