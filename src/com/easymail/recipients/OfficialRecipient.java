package com.easymail.recipients;

import java.util.Arrays;
import java.util.List;

public class OfficialRecipient extends Recipient{
    public String designation;

    public OfficialRecipient(String name, String email, String designation) {
        this.name = name;
        this.email = email;
        this.designation = designation;
    }
    public OfficialRecipient(String record) {
        record = record.substring(9);
        List<String> list = Arrays.asList(record.split(","));
        this.name = list.get(0);
        this.email = list.get(1);
        this.designation = list.get(2);
    }
    @Override
    public String toString() {
       return "official:" + String.join(",", name, email, designation);
    }
}
