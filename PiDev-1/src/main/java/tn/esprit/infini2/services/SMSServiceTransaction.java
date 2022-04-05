package tn.pidev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import tn.pidev.twilio.dto.SMSRequestTransaction;

@org.springframework.stereotype.Service
public class SMSServiceTransaction {

    private final ISMSSenderTransaction smsSender;

    @Autowired
    public SMSServiceTransaction(@Qualifier("twilio") SMSSenderTransactionImpl smsSender) {
        this.smsSender = smsSender;
    }

    public String sendSms(SMSRequestTransaction smsRequest) {
        return smsSender.sendSms(smsRequest);
    }
}
