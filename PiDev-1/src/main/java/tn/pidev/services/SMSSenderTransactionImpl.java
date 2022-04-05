package tn.pidev.services;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;

import tn.pidev.configurations.TwilioConfigurationCredit;
import tn.pidev.twilio.dto.SMSRequestTransaction;
import java.text.DecimalFormat;
import java.util.Random;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("twilio")
public class SMSSenderTransactionImpl implements ISMSSenderTransaction {

    private static final Logger LOGGER = LoggerFactory.getLogger(SMSSenderTransactionImpl.class);

    private final TwilioConfigurationCredit twilioConfiguration;

    @Autowired
    public SMSSenderTransactionImpl(TwilioConfigurationCredit twilioConfiguration) {
        this.twilioConfiguration = twilioConfiguration;
    }

    @Override
    public String sendSms(SMSRequestTransaction smsRequest) {
    	String otp= generateOTP();
        if (isPhoneNumberValid(smsRequest.getPhoneNumber())) {
            PhoneNumber to = new PhoneNumber(smsRequest.getPhoneNumber());
            PhoneNumber from = new PhoneNumber(twilioConfiguration.getTrialNumber());
			String message = "Dear Customer , Your OTP is ##" + otp + "##. Use this Passcode to complete your transaction. Thank You.";
            MessageCreator creator = Message.creator(to, from, message);
            creator.create();
            LOGGER.info("Send sms {}", smsRequest);
        } else {
            throw new IllegalArgumentException(
                    "Phone number [" + smsRequest.getPhoneNumber() + "] is not a valid number"
            );
     
        }
		return otp;
    }
    public String generateOTP() {
        return new DecimalFormat("000000")
                .format(new Random().nextInt(999999));
    }

    private boolean isPhoneNumberValid(String phoneNumber) {
//    	String pattern = "\\d{8}$";
//    	if (phoneNumber.matches(pattern)) {     
    			return true;
//    	} else {     
//    	   
//    		return false; 
//    	} 
    }
}
