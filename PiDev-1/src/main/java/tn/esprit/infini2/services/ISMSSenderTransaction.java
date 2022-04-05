package tn.pidev.services;

import tn.pidev.twilio.dto.SMSRequestTransaction;

public interface ISMSSenderTransaction {

    String sendSms(SMSRequestTransaction smsRequest);

    String generateOTP();
    // or maybe void sendSms(String phoneNumber, String message);
}
