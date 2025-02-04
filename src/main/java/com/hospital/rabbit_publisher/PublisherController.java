package com.hospital.rabbit_publisher;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(path = "/publisher")
@RequiredArgsConstructor
public class PublisherController {

    private final RabbitTemplate template;

    @PostMapping("paciente")
    public String publishPaciente(@RequestBody Models.Paciente paciente) {
        this.template.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY_PACIENTE, paciente);
        return "Paciente enviado";
    }

    @PostMapping("senales-vitales")
    public String publishSenalesVitales(@RequestBody Models.SenalesVitales senalesVitales) {
        this.template.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY_SENALES_VITALES,
                senalesVitales);
        return "Señales vitales enviadas";
    }

    @PostMapping("mensaje-alerta")
    public String publishMensajeAlerta(@RequestBody Models.MensajeAlerta mensajeAlerta) {
        this.template.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY_MENSAJE_ALERTA,
                mensajeAlerta);
        return "Mensaje de alerta enviado";
    }
}