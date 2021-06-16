package com.gustavotorres.cadastroconta.messages;

import com.gustavotorres.cadastroconta.dtos.PessoaCadastroInputDTO;
import com.gustavotorres.cadastroconta.services.ContaService;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class CriaContaPessoaMessageListener {
    
    @Autowired
    private ContaService contaService; 
    
    @RabbitListener(queues = {"${constants.rabbitmq.queue}"})
    public void recieve (@Payload PessoaCadastroInputDTO pessoaCadastroDTO) {
        contaService.criarConta(pessoaCadastroDTO);
    }
}