package com.easymail.historyManagement;

// create and manages EmailHistory object per day

import com.easymail.emailSender.Email;

import java.io.*;

public class MailsInADay {
    private EmailHistoryObjects emailHistoryObjects;
    private static MailsInADay mailsInADay;

    private MailsInADay() {} // just to make constructor private (Singleton)

    public static void createInstance() {
        mailsInADay = new MailsInADay();
        File historyFile = new File("historySave.txt");
        if (!historyFile.exists()) {
            mailsInADay.emailHistoryObjects = new EmailHistoryObjects();
        }
        else {
            try {
                ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(historyFile));
                mailsInADay.emailHistoryObjects = (EmailHistoryObjects) objectInputStream.readObject();
            }
            catch (IOException | ClassNotFoundException ex) {
                System.out.println("Error while reading history file!");
            }
        }
    }

    public static boolean isNotCreated() {
        return mailsInADay == null;
    }

    public static void save(Email email) {
        mailsInADay.emailHistoryObjects.addToHistory(email);
    }

    public static void printHistory(String dateString) {
        var emailsInTheDay = mailsInADay.emailHistoryObjects.getHistory().get(dateString);
        for (Email email : emailsInTheDay)
            System.out.println(email);
    }

    public static void close() {
        try {
            File historyFile = new File("historySave.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(historyFile));
            objectOutputStream.writeObject(historyFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
