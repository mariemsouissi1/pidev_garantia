package tn.esprit.infini2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import tn.esprit.infini2.entities.SmsRequest;

@org.springframework.stereotype.Service
public class 	TwilioService {

    private final SmsSender smsSender;

    @Autowired
    public TwilioService(@Qualifier("twilio") TwilioSmsSender smsSender) {
        this.smsSender = smsSender;
    }

    public void sendSms(SmsRequest smsRequest) {
        smsSender.sendSms(smsRequest);
    }
}
