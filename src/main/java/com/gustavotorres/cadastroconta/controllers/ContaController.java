package com.gustavotorres.cadastroconta.controllers;

import javax.validation.Valid;

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

    @GetMapping(produces = {"application/json","application/xml","application/x-yaml"})
        public ResponseEntity<?> findAll() {

            List<ContaDTO> listOfContas = contaService.findAll();

            return new ResponseEntity<>(listOfContas,HttpStatus.OK);

    }

    @PostMapping(produces = {"application/json","application/xml","application/x-yaml"},
                consumes = {"application/json","application/xml","application/x-yaml"})
    public ResponseEntity<?> criarConta(@Valid @RequestBody PessoaCadastroInputDTO pessoaCadastroInputDTO) {
        ContaDTO contaDTORetorno = contaService.criarConta(pessoaCadastroInputDTO);

        return new ResponseEntity<>(contaDTORetorno,HttpStatus.CREATED);
    }

}