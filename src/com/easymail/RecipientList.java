package com.easymail;

// manages contact list

import com.easymail.recipients.FriendRecipient;
import com.easymail.recipients.OfficeFriendRecipient;
import com.easymail.recipients.OfficialRecipient;
import com.easymail.recipients.Recipient;

import java.io.*;
import java.util.*;

public class RecipientList {
    public static int recipientCount = 0;
    private File clientList;
    private static RecipientList recipientList = new RecipientList();
    private static File countToText;

    private RecipientList() {
        clientList = new File("clientList.txt");
    }

    public static void addRecipient(Recipient recipient) {
        try {
            var writer = new PrintWriter(new BufferedWriter(new FileWriter(recipientList.clientList, true)));
            writer.println(recipient.toString());
            System.out.println("Adding recipient successful!");
            writer.flush();
            recipientCount++;
        }
        catch (IOException ex) {
            System.out.println("Error occurred while writing to the file!");
        }
    }

    // reads clientList.txt and returns an ArrayList of Recipient objects
    public static Map<String, Recipient> readRecipientList() {
        Map<String, Recipient> recipientList = new HashMap<>();
        String record;
        try {
            var reader = new Scanner(RecipientList.recipientList.clientList);
            while (reader.hasNextLine()) {
                record = reader.nextLine();
                Recipient recipient;
                if (record.contains("official")) {
                    recipient = new OfficialRecipient(record);
                    recipientList.put(recipient.name.toLowerCase(), recipient);
                }
                else if (record.contains("Office_friend")) {
                    recipient = new OfficeFriendRecipient(record);
                    recipientList.put(recipient.name.toLowerCase(), recipient);
                }
                else if (record.contains("Personal")) {
                    recipient = new FriendRecipient(record);
                    recipientList.put(recipient.name.toLowerCase(), recipient);
                }
            }
            reader.close();
        }
        catch (FileNotFoundException ex) {} // try this error too
        return recipientList;
    }

    public static void cleanRecipientList() {
        try {
            new FileWriter(recipientList.clientList, false).close();
            recipientCount = 0;
            System.out.println("All contacts deleted successfully!");
        }
        catch (IOException ex) {
            System.out.println("Error occurred while cleaning recipient list!");
        }
    }

    // starting recipient list
    // creating recipient list object with clientList.txt
    // reading pre-saved recipient count if exists
    public static void start() {
        // update contact count
        countToText = new File("countToText.txt");
        try {
            var reader = new Scanner(countToText);
            recipientCount = reader.nextInt();
            reader.close();
        }
        catch (FileNotFoundException  ex) {} // try to solve this error later : empty catch statement
    }

    // saving recipient count into an external file
    public static void close() {
        try {
            var writer = new PrintWriter(new BufferedWriter(new FileWriter(countToText, false)));
            writer.println(recipientCount);
            writer.flush();
        }
        catch (IOException ex) {
            System.out.println("Error occurred while closing the program!");
        }
    }
}