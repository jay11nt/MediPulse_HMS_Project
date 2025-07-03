package com.ho.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.twilio.Twilio;

import jakarta.annotation.PostConstruct;
import lombok.Getter;

@Configuration
@Getter
public class TwilioConfig {

    @Value("${twilio.account.sid}")
    private String accountSid;

    @Value("${twilio.auth.token}")
    private String authToken;

    @Value("${twilio.phone.number}")
    private String fromNumber;
    
    @PostConstruct
    public void initTwilio() {
        Twilio.init(accountSid, authToken);
    }
}
