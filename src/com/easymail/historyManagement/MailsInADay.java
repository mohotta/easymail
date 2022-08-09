package com.easymail.historyManagement;

// create and manages EmailHistory object for each day

import com.easymail.emailSender.Email;

import java.io.*;

public class MailsInADay {
    private EmailHistoryObjects emailHistoryObjects;
    private static MailsInADay mailsInADay;

    private MailsInADay() {} // just to make constructor private (Singleton)

    public static MailsInADay getInstance() {
        if (isNotCreated()) {
            mailsInADay = new MailsInADay();
            File historyFile = new File("historySave.txt");
            if (!historyFile.exists()) {
                mailsInADay.emailHistoryObjects = new EmailHistoryObjects();
            } else {
                try {
                    ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(historyFile));
                    mailsInADay.emailHistoryObjects = (EmailHistoryObjects) objectInputStream.readObject();
                } catch (IOException | ClassNotFoundException ex) {
                    System.out.println("Error while reading history file!");
                }
            }
        }
        return mailsInADay;
    }

    private static boolean isNotCreated() {
        return mailsInADay == null;
    }

    public void save(Email email) {
        emailHistoryObjects.addToHistory(email);
    }

    public void printHistory(String dateString) {
        var emailsInTheDay = emailHistoryObjects.getHistory().get(dateString);
        for (Email email : emailsInTheDay)
            System.out.println(email);
    }

    public static void close() {
        var mailsInADayToSave = getInstance();
        try {
            File historyFile = new File("historySave.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(historyFile));
            objectOutputStream.writeObject(mailsInADayToSave.emailHistoryObjects);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
