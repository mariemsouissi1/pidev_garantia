package tn.esprit.infini2.services;

import tn.esprit.infini2.entities.SmsRequest;

public interface SmsSender {

    void sendSms(SmsRequest smsRequest);

    // or maybe void sendSms(String phoneNumber, String message);
}