package com.gustavotorres.cadastroconta.controllers;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import com.gustavotorres.cadastroconta.dtos.ContaDTO;
import com.gustavotorres.cadastroconta.dtos.PessoaCadastroInputDTO;
import com.gustavotorres.cadastroconta.services.ContaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

@RestController
@RequestMapping("/contas")
@Api(value = "API Rest Contas")
public class ContaController {
    
    @Autowired
    private ContaService contaService;

    @Autowired
    private PagedResourcesAssembler<ContaDTO> assembler;

    @ApiOperation("Buscar Lista de Contas")
    @GetMapping(produces = {"application/json","application/xml","application/x-yaml"})
        public ResponseEntity<?> findAll(@RequestParam(value = "page", defaultValue = "0") int page,    
        @RequestParam(value = "size", defaultValue = "12") int size,    
        @RequestParam(value = "sort", defaultValue = "asc") String sort
    ) {

            var sortDirection = "desc".equalsIgnoreCase(sort) ? Direction.DESC : Direction.ASC;

            Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection,"numeroConta"));

            Page<ContaDTO> pagedListContaDTO = contaService.findAll(pageable);

            pagedListContaDTO.stream().forEach(p -> {p.add(linkTo(methodOn(ContaController.class).findById(p.getId())).withSelfRel());});

            PagedModel<EntityModel<ContaDTO>> pagedModel = assembler.toModel(pagedListContaDTO);


            return new ResponseEntity<>(pagedModel,HttpStatus.OK);

    }

    @GetMapping(value = "/{id}",
                produces = {"application/json","application/xml","application/x-yaml"})
    @ApiOperation("Buscar Conta pelo Id")
    public ResponseEntity<?> findById(@PathVariable Long id) {
            ContaDTO contaDTO = contaService.findById(id);
            contaDTO.add(linkTo(methodOn(ContaController.class).findById(contaDTO.getId())).withSelfRel());
            return new ResponseEntity<>(contaDTO,HttpStatus.OK);

    }

    @PostMapping(produces = {"application/json","application/xml","application/x-yaml"},
                consumes = {"application/json","application/xml","application/x-yaml"})
    @ApiOperation("Cadastrar nova Conta para Pessoa")
    public ResponseEntity<?> criarConta(@Valid @RequestBody PessoaCadastroInputDTO pessoaCadastroInputDTO) {
        ContaDTO contaDTORetorno = contaService.criarConta(pessoaCadastroInputDTO);
        contaDTORetorno.add(linkTo(methodOn(ContaController.class).findById(contaDTORetorno.getId())).withSelfRel());

        return new ResponseEntity<>(contaDTORetorno,HttpStatus.CREATED);
    }

}
