package com.easymail.recipients.bluePrints;


import com.easymail.customSupportLibraries.DateOperations;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class FriendRecipient extends Recipient{
    public String nickName;
    public Date birthDay;

    public FriendRecipient(String name, String email, String nickName, Date birthDay) {
        this.name = name;
        this.email = email;
        this.nickName = nickName;
        this.birthDay = birthDay;
    }
    public FriendRecipient(String record) {
        record = record.substring(9);
        List<String> list = Arrays.asList(record.split(","));
        this.name = list.get(0);
        this.email = list.get(2);
        this.nickName = list.get(1);
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
        return "Personal:" + String.join(",", name, nickName, email, DateOperations.dateToString(birthDay));
    }
}
