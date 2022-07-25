package com.easymail.customSupportLibraries;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class GetString {

    public static Date getDate() {
        var input = new Scanner(System.in);
        while (true) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            try {
                return dateFormat.parse(input.next());
            }
            catch (ParseException ex) {
                System.out.print("Invalid input, Enter again!");
            }
        }
    }

    public static String getEmail() { // get valid email
        var input = new Scanner(System.in);
        String email;
        while (true) {
            email = input.nextLine();
            if (validateEmail(email))
                break;
            else
                System.out.print("Invalid Input, Enter again: ");
        }
        return email;
    }

    // takes only one line of input
    public static String getNormalString() {
        var input = new Scanner(System.in);
        var string = input.nextLine();
        return string;
    }

    private static boolean validateEmail(String email) {
        boolean valid;
        try {
            var address = new InternetAddress(email);
            address.validate();
            valid = true;
        }
        catch (AddressException ex) {
            valid = false;
        }
        return valid;
    }
}
