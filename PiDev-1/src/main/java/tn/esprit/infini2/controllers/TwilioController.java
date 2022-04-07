package tn.esprit.infini2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.infini2.entities.SmsRequest;
import tn.esprit.infini2.services.TwilioService;

//http://localhost:8089/SpringMVC/api/v1/sms

@RestController
@RequestMapping("api/v1/sms")
public class TwilioController {

    private final TwilioService service;

    @Autowired
    public TwilioController(TwilioService service) {
        this.service = service;
    }

    @PostMapping
    public void sendSms(@Validated @RequestBody SmsRequest smsRequest) {
        service.sendSms(smsRequest);
    }
}
