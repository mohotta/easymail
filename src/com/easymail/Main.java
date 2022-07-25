package com.easymail;

// B.M.K.L.Mohottala
// 200399G

import com.easymail.customSupportLibraries.GetNumber;
import com.easymail.customSupportLibraries.GetString;
import com.easymail.emailSender.Email;
import com.easymail.emailSender.EmailCreator;
import com.easymail.emailSender.EmailSenderJavaMail;
import com.easymail.emailSender.IEmailSender;
import com.easymail.historyManagement.MailsInADay;
import com.easymail.recipients.FriendRecipient;
import com.easymail.recipients.OfficeFriendRecipient;
import com.easymail.recipients.OfficialRecipient;

public class Main {

    public static void main(String[] args) {

        Program.start();

        while (true) {

            byte choice;

            String line = new String(new char[50]).replace('\0', '-');

            System.out.println(line);
            System.out.println(new String(new char[15]).replace('\0', '-') +
                    " EasyMail - 200399G " +
                    new String(new char[15]).replace('\0', '-'));
            System.out.printf("%s : %s\n", "Enter no: 1 ", "Add new recipient");
            System.out.printf("%s : %s\n", "Enter no: 2 ", "Send an email");
            System.out.printf("%s : %s\n", "Enter no: 3 ", "Print today's birthdays");
            System.out.printf("%s : %s\n", "Enter no: 4 ", "Print email history of a day");
            System.out.printf("%s : %s\n", "Enter no: 5 ", "Print no: of recipients");
            System.out.printf("%s : %s\n", "Enter no: 6 ", "Delete all saved contacts");
            System.out.printf("%s : %s\n", "Enter no: 0 ", "Close the application");
            System.out.println(line);

            System.out.print("Enter Your Choice : ");
            choice = GetNumber.getByte((byte) 0, (byte) 6);

            switch (choice) {
                case 0:
                    System.out.println(line);
                    System.out.println(new String(new char[19]).replace('\0', '-') +
                            " Good Bye! " +
                            new String(new char[20]).replace('\0', '-'));
                    System.out.println(line);

                    Program.close();
                    return;

                case 1: // add recipient
                    byte contactType;

                    System.out.println(new String(new char[14]).replace('\0', '-') +
                            " Adding New Recipient " +
                            new String(new char[14]).replace('\0', '-'));
                    System.out.println("Enter recipient type,");
                    System.out.println("\t1 to Official Contacts");
                    System.out.println("\t2 to Official Friends");
                    System.out.println("\t3 to Friends' Contacts");

                    System.out.print("Enter your choice: ");
                    contactType = GetNumber.getByte((byte) 1, (byte) 3);
                    System.out.print("Enter name: ");
                    var name = GetString.getNormalString();
                    System.out.print("Enter email address: ");
                    var email = GetString.getEmail();

                    switch (contactType) {
                        case 1 -> {
                            System.out.print("Enter designation: ");
                            var designationOfficial = GetString.getNormalString();
                            var officialRecipient = new OfficialRecipient(name, email, designationOfficial);
                            RecipientList.addRecipient(officialRecipient);
                        }
                        case 2 -> {
                            System.out.print("Enter designation: ");
                            var designationOffFriend = GetString.getNormalString();
                            System.out.print("Enter birthday (yyyy/mm/dd): ");
                            var birthdayOffFriend = GetString.getDate();
                            var officeFriend = new OfficeFriendRecipient(name, email, designationOffFriend, birthdayOffFriend);
                            RecipientList.addRecipient(officeFriend);
                        }
                        case 3 -> {
                            System.out.print("Enter nickname (if not skip enter 0): ");
                            var nickName = GetString.getNormalString();
                            if (nickName == "0") // asking for zero to prevent mixing up future inputs
                                nickName = "<no-nick-name>";
                            System.out.print("Enter birthday (yyyy/mm/dd): ");
                            var birthdayFriend = GetString.getDate();
                            var friend = new FriendRecipient(name, email, nickName, birthdayFriend);
                            RecipientList.addRecipient(friend);
                        }
                    }
                    break;

                case 2: // send email
                    Email emailObj = EmailCreator.createEmail();
                    IEmailSender mailSender = new EmailSenderJavaMail();
                    mailSender.sendEmail(emailObj);
                    if (MailsInADay.isNotCreated())
                        MailsInADay.createInstance();
                    MailsInADay.save(emailObj);
                    break;

                case 3: // today birthday
                    System.out.println(new String(new char[14]).replace('\0', '-') +
                            " Today's Birthday List " +
                            new String(new char[14]).replace('\0', '-'));
                    RecipientBirthday.printTodayBirthday();
                    break;

                case 4: // email history
                    System.out.print("Enter the date you want see history (yyyy/mm/dd): ");
                    String dateString = GetString.getNormalString();
                    if (MailsInADay.isNotCreated())
                        MailsInADay.createInstance();
                    MailsInADay.printHistory(dateString);
                    break;

                case 5: // no of recipients
                    System.out.println(new String(new char[14]).replace('\0', '-') +
                            " Number of Recipients " +
                            new String(new char[14]).replace('\0', '-'));

                    System.out.println("Current no: of recipients: " + RecipientList.recipientCount);
                    break;

                case 6: // delete all recipients
                    System.out.println(new String(new char[13]).replace('\0', '-') +
                            " Deleting All Contacts " +
                            new String(new char[14]).replace('\0', '-'));

                    RecipientList.cleanRecipientList();
                    break;
            }
        }
    }
}
