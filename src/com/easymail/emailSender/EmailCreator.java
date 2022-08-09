package com.easymail.emailSender;

// creating email objects from input

import com.easymail.recipients.RecipientList;
import com.easymail.customSupportLibraries.GetNumber;
import com.easymail.customSupportLibraries.GetString;

public class EmailCreator {

    private Email email;

    public Email createEmail() {
        var toRecipient = getEmailInput("receiver");

        System.out.print("How many carbon copies you want? ");
        int countCC = GetNumber.getNormalInt();
        String[] carbonCopies = new String[countCC];
        for (int i = 0; i < countCC; i++) {
            carbonCopies[i] = getEmailInput("Carbon copy" + i+1);
        }

        System.out.print("How many blind carbon copies you want? ");
        int countBCC = GetNumber.getNormalInt();
        String[] blindCarbonCopies = new String[countBCC];
        for (int i = 0; i < countBCC; i++) {
            blindCarbonCopies[i] = getEmailInput("Blind carbon copy" + i+1);
        }

        System.out.print("Enter email subject: ");
        var subject = GetString.getNormalString();

        System.out.print("Enter Content of the mail: ");
        var content = GetString.getNormalString();

        return new Email(toRecipient, carbonCopies, blindCarbonCopies, subject, content);
    }

    private String getEmailInput(String receiverType) {
        String email = null;
        System.out.println("Enter " + receiverType + ": ");
        System.out.println("\tEnter 1 to search by name: ");
        System.out.println("\tEnter 2 to enter email: ");
        System.out.print("Enter you choice: ");
        byte choice = GetNumber.getByte((byte) 1, (byte) 2);

        switch (choice) {
            case 1 -> {
                System.out.print("Enter receiver's name (must be in contact list): ");
                while (true) {
                    var name = GetString.getNormalString();
                    var emailValue = searchEmailByName(name);
                    if (emailValue == null) {
                        System.out.print("Name not found, Try again: ");
                    } else {
                        email = emailValue;
                        break;
                    }
                }
            }
            case 2 -> {
                System.out.print("Enter receiver's email: ");
                email = GetString.getEmail();
            }
        }
        return email;
    }

    private String searchEmailByName(String name) {
        var recipientList = RecipientList.readRecipientList();
        if (recipientList.containsKey(name.toLowerCase()))
            return recipientList.get(name).email;
        else
            return null; // name not found
    }
}
