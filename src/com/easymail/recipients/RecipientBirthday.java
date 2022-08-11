package com.easymail.recipients;

// manages birthday related things

import com.easymail.Program;
import com.easymail.customSupportLibraries.DateOperations;
import com.easymail.emailSender.Email;
import com.easymail.emailSender.EmailSenderJavaMail;
import com.easymail.emailSender.IEmailSender;
import com.easymail.recipients.bluePrints.FriendRecipient;
import com.easymail.recipients.bluePrints.OfficeFriendRecipient;
import com.easymail.recipients.bluePrints.OfficialRecipient;
import com.easymail.recipients.bluePrints.Recipient;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RecipientBirthday {
    private List<Recipient> todayBirthdays;
    private static RecipientBirthday recipientBirthday = new RecipientBirthday();
    private static String wishMailSubject = "Happy Birthday!";
    private static String officeFriendWish = "Wish you a Happy Birthday!!!\nKumudu Mohottala";
    private static String friendWish = "Hugs and love on your birthday!!!\nKumudu Mohottala";

    private RecipientBirthday() {
        var recipientArr = new ArrayList<>(RecipientList.readRecipientList().values());
        todayBirthdays = new ArrayList<>();
        for (int i = 0; i < recipientArr.size(); i++) {
            if (isTodayBirthday(recipientArr.get(i)))
                todayBirthdays.add(recipientArr.get(i));
        }
    }

    public static void updateBirthdays(Recipient recipient) {
        if (isTodayBirthday(recipient))
            recipientBirthday.todayBirthdays.add(recipient);
    }

    public static void printTodayBirthday() {
        if (recipientBirthday.todayBirthdays.isEmpty())
            System.out.println("No birthdays today!");
        for (var recipient : recipientBirthday.todayBirthdays)
            System.out.println(recipient.name + " : " + recipient.email);
    }

    // sending birthday wishes when starting
    public static void start() {
        // if there are no birthdays today no need to go through whole process
        if (recipientBirthday.todayBirthdays.isEmpty())
            return;
        // if program opened today emails must be already sent
        if (DateOperations.isToday(Program.getLastOpenedDay()))
            return;
        System.out.println("Sending birthday wishes...");
        IEmailSender sender = new EmailSenderJavaMail();
        for (var recipient : recipientBirthday.todayBirthdays) {
            // birthday list cannot contain official recipients, so we don't need to filter them again
            Email email;
            if (recipient instanceof OfficeFriendRecipient)
                email = new Email(recipient.email, wishMailSubject, officeFriendWish);
            else
                email = new Email(recipient.email, wishMailSubject, friendWish);
            sender.sendEmail(email);
        }
    }

    // check whether today is birthday
    private static boolean isTodayBirthday(Recipient recipient) {
        if (recipient instanceof OfficialRecipient)
            return false;
        Date birthday;
        if (recipient instanceof OfficeFriendRecipient)
            birthday = ((OfficeFriendRecipient)recipient).birthDay;
        else
            birthday = ((FriendRecipient)recipient).birthDay;

        return DateOperations.isTodayBirthday(birthday);
    }
}
