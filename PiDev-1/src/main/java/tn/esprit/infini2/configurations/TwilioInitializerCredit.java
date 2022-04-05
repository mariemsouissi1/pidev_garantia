package tn.pidev.configurations;

import com.twilio.Twilio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TwilioInitializerCredit {

    private final static Logger log = LoggerFactory.getLogger(TwilioInitializerCredit.class);

    private final TwilioConfigurationCredit twilioConfiguration;

    @Autowired
    public TwilioInitializerCredit(TwilioConfigurationCredit twilioConfiguration) {
        this.twilioConfiguration = twilioConfiguration;
        Twilio.init(
                twilioConfiguration.getAccountSid(),
                twilioConfiguration.getAuthToken()
        );
        log.info("Twilio initialized with account sid {} ", twilioConfiguration.getAccountSid());
    }
}
