package com.tanlaplatforms.hrishikeshpatil.spring_rabbitmq.controller;

import lombok.AllArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class MessageController {

    private final RabbitTemplate rabbitTemplate;

    @PostMapping("/{id}")
    public String sendMessage(@RequestBody String body, @PathVariable String id) {
        this.rabbitTemplate.convertAndSend(id, body);
        return body;
    }

    @GetMapping("/{id}")
    public String getMessage(@PathVariable String id) {
        Message message = this.rabbitTemplate.receive(id);
        if (message != null)
            return message.toString();
        else
            return null;
    }

}
