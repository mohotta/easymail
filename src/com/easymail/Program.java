package com.easymail;

// start and end components in the program

import com.easymail.customSupportLibraries.DateOperations;
import com.easymail.historyManagement.MailsInADay;
import com.easymail.recipients.RecipientBirthday;
import com.easymail.recipients.RecipientList;

import java.io.*;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Scanner;

// start and close program
// keep track of last opened date
public class Program {
    private File dateSave = new File("dateSave.txt");
    private static Program program;
    private static Date lastOpenedDay;

    private Program() {
        try { // grab last opened day
            var reader = new Scanner(dateSave);
            String dateString = reader.nextLine();
            lastOpenedDay = DateOperations.dateFromString(dateString);
        }
        catch (FileNotFoundException | NoSuchElementException ex) {
            lastOpenedDay = new Date(); // create new date if this is first time opening
        }
    }

    public static void start() {
        program = new Program();

        RecipientList.start();
        RecipientBirthday.start();
    }

    public static void close() {
        RecipientList.close(); // recipient count must be saved in every program close
        lastOpenedDay = new Date();
        // saving today as last opened day
        try {
            var writer = new PrintWriter(new BufferedWriter(new FileWriter(program.dateSave, false)));
            writer.print(DateOperations.dateToString(lastOpenedDay));
            writer.flush();
        }
        catch (IOException ex) {
            System.out.println("Error while saving last operated day!");
        }
        MailsInADay.close();
    }

    public static Date getLastOpenedDay() {
        return lastOpenedDay;
    }
}
