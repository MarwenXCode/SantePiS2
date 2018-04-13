/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package santepis2.services;

import santepis2.entities.Sms;
import santepis2.services.interfaces.ISms;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class SmsService implements ISms{
    public static final String ACCOUNT_SID = "ACc1f576ef89513cecf0b00b7018e11878";
    public static final String AUTH_TOKEN = "063beffa09abc688f6fa6b786032a328";
    @Override
    public void sendSms(Sms sms) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
            Message msg = Message.creator(new PhoneNumber(sms.getNum()), new PhoneNumber("+19412081285"), sms.getMessagetel()).create();
            System.out.println(msg.getSid());
}
}