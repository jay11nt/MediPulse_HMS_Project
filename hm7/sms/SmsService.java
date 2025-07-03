package com.ho.sms;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SmsService {

    @Value("${twilio.phone.number}")
    private String twilioPhoneNumber;

    public void sendSms(String to, String messageBody) {
        Message.creator(
                new PhoneNumber(to),           // To number
                new PhoneNumber(twilioPhoneNumber),  // From Twilio number
                messageBody
        ).create();
    }
}
