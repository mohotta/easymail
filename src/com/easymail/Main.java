package com.easymail;

// B.M.K.L.Mohottala
// 200399G

import com.easymail.customSupportLibraries.DateOperations;
import com.easymail.customSupportLibraries.GetNumber;
import com.easymail.customSupportLibraries.GetString;
import com.easymail.emailSender.Email;
import com.easymail.emailSender.EmailCreator;
import com.easymail.emailSender.EmailSenderJavaMail;
import com.easymail.emailSender.IEmailSender;
import com.easymail.historyManagement.MailsInADay;
import com.easymail.recipients.RecipientBirthday;
import com.easymail.recipients.RecipientList;
import com.easymail.recipients.generators.FriendRecipientGenerator;
import com.easymail.recipients.generators.OfficeFriendRecipientGenerator;
import com.easymail.recipients.generators.OfficialRecipientGenerator;
import com.easymail.recipients.generators.RecipientGenerator;

import java.util.Date;

public class Main {

    public static void main(String[] args) {

        Program.start();

        printBorder();
        centerString(" EasyMail - 200399G ", 15, 15, '=');

        while (true) {

            printBorder();
            printIntro();
            printBorder();

            byte choice;
            System.out.print("Enter Your Choice : ");
            choice = GetNumber.getByte((byte) 0, (byte) 6);
            printBorder();

            switch (choice) {
                case 0 -> { // close program
                    centerString(" Good Bye! ", 19, 20, '=');
                    printBorder();
                    Program.close();
                    return;
                }
                case 1 -> { // add recipient
                    centerString(" Adding New Recipient ", 14, 14, '=');
                    printRecipientIntro();
                    byte contactType;
                    System.out.print("Enter your choice: ");
                    contactType = GetNumber.getByte((byte) 1, (byte) 3);
                    switch (contactType) {
                        case 1 -> {
                            RecipientGenerator officialRecipientGenerator =
                                    new OfficialRecipientGenerator();
                            officialRecipientGenerator.generateRecipient();
                        }
                        case 2 -> {
                            RecipientGenerator officeFriendRecipientGenerator =
                                    new OfficeFriendRecipientGenerator();
                            officeFriendRecipientGenerator.generateRecipient();
                        }
                        case 3 -> {
                            RecipientGenerator friendRecipientGenerator =
                                    new FriendRecipientGenerator();
                            friendRecipientGenerator.generateRecipient();
                        }
                    }
                }
                case 2 -> { // send email
                    centerString(" Sending Email(s) ", 16, 16, '=');
                    EmailCreator emailCreator = new EmailCreator();
                    Email emailObj = emailCreator.createEmail();
                    IEmailSender mailSender = new EmailSenderJavaMail();
                    mailSender.sendEmail(emailObj);
                }
                case 3 -> { // today birthday
                    centerString(" Today's Birthday List ", 14, 14, '=');
                    RecipientBirthday.printTodayBirthday();
                }
                case 4 -> { // email history
                    centerString(" Email History of a Day ", 13, 13, '=');
                    System.out.print("Enter the date (yyyy/mm/dd): ");
                    Date date = GetString.getDate();
                    String dateString = DateOperations.dateToString(date);
                    MailsInADay mailsInADay = MailsInADay.getInstance();
                    mailsInADay.printHistory(dateString);
                }
                case 5 -> { // no of recipients
                    centerString(" Number of Recipients ", 14, 14, '=');
                    System.out.println("Current no: of recipients: " + RecipientList.recipientCount);
                }
                case 6 -> { // delete all recipients
                    centerString(" Deleting All Contacts ", 14, 14, '=');
                    RecipientList.cleanRecipientList();
                }
            }
        }
    }

    public static void printBorder() {
        System.out.println(new String(new char[50]).replace('\0', '='));
    }

    public static void printIntro() {
        System.out.printf("%s : %s\n", "Enter no: 1 ", "Add new recipient");
        System.out.printf("%s : %s\n", "Enter no: 2 ", "Send an email");
        System.out.printf("%s : %s\n", "Enter no: 3 ", "Print today's birthdays");
        System.out.printf("%s : %s\n", "Enter no: 4 ", "Print email history of a day");
        System.out.printf("%s : %s\n", "Enter no: 5 ", "Print no: of recipients");
        System.out.printf("%s : %s\n", "Enter no: 6 ", "Delete all saved contacts");
        System.out.printf("%s : %s\n", "Enter no: 0 ", "Close the application");
    }

    public static void printRecipientIntro() {
        System.out.println("Enter recipient type,");
        System.out.printf("\t%s : %s\n", "Enter no: 1","for Official Contacts");
        System.out.printf("\t%s : %s\n", "Enter no: 2","for Official Friends");
        System.out.printf("\t%s : %s\n", "Enter no: 3","for Contacts");
    }

    public static void centerString(String string, int left, int right, char character) {
        System.out.println(new String(new char[left]).replace('\0', character) +
                string +
                new String(new char[right]).replace('\0', character));
    }
}

