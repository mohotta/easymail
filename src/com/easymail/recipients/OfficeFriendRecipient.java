package com.easymail.recipients;

import com.easymail.customSupportLibraries.DateOperations;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class OfficeFriendRecipient extends Recipient{
    public String designation;
    public Date birthDay;

    public OfficeFriendRecipient(String name, String email, String designation, Date birthDay) {
        this.name = name;
        this.email = email;
        this.designation = designation;
        this.birthDay = birthDay;
    }
    public OfficeFriendRecipient(String record) {
        record = record.substring(14);
        List<String> list = Arrays.asList(record.split(","));
        this.name = list.get(0);
        this.email = list.get(1);
        this.designation = list.get(2);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        try {
            this.birthDay = dateFormat.parse(list.get(3));
        }
        catch (ParseException ex) {
            System.out.println("Error occurred while reading the record!");
        }
    }
    @Override
    public String toString() {
        return "Office_friend:" + String.join(",", name, email, designation, DateOperations.dateToString(birthDay));
    }
}
