package com.easymail.recipients.generators;

import com.easymail.recipients.RecipientList;
import com.easymail.customSupportLibraries.GetString;
import com.easymail.recipients.bluePrints.Recipient;

public abstract class RecipientGenerator {

    public void generateRecipient() {
        System.out.print("Enter name: ");
        String name = GetString.getNormalString();
        System.out.print("Enter email address: ");
        String email = GetString.getEmail();

        Recipient recipient = recipientFactory(name, email);
        RecipientList.addRecipient(recipient);
    }

    protected abstract Recipient recipientFactory(String name, String email);
}
