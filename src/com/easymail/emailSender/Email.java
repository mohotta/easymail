package com.easymail.emailSender;

// defines email object

import com.easymail.customSupportLibraries.DateOperations;

import java.io.Serializable;
import java.util.Date;

public class Email implements Serializable {
    public String toRecipient;
    public String[] carbonCopy;
    public String[] blindCarbonCopy;
    public String subject;
    public String content;
    public Date sentDate;

    // for ordinary emails
    public Email(String toRecipient, String[] carbonCopy, String[] blindCarbonCopy, String subject, String content) {
        this.toRecipient = toRecipient;
        this.carbonCopy = carbonCopy;
        this.blindCarbonCopy = blindCarbonCopy;
        this.subject = subject;
        this.content = content;
        this.sentDate = new Date();
    }

    // for automated emails without cc and bcc
    public Email(String toRecipient, String subject, String content) {
        this.toRecipient = toRecipient;
        this.carbonCopy = new String[0];
        this.blindCarbonCopy = new String[0];
        this.subject = subject;
        this.content = content;
        this.sentDate = new Date();
    }

    @Override
    public String toString() {
        String string = new String(new char[50]).replace('\0', '-');
        string += "\n";
        string += "To : " + toRecipient + "\n";
        string += "Cc : ";
        for (String cc : carbonCopy)
            string += cc + ", ";
        string += "\n";
        string += "Bcc : ";
        for (String bcc : blindCarbonCopy)
            string += bcc + ", ";
        string += "\n";
        string += "sent on : " + DateOperations.dateToTimeString(sentDate) + "\n";
        string += "Subject : " + subject + "\n";
        string += new String(new char[50]).replace('\0', '-');
        return string;
    }
}
