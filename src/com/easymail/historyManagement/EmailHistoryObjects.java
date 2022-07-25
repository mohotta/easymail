package com.easymail.historyManagement;

import com.easymail.customSupportLibraries.DateOperations;
import com.easymail.emailSender.Email;

import java.util.*;

public class EmailHistoryObjects{
    private Map<String, List<Email>> history;

    public EmailHistoryObjects() {
        history = new HashMap<>();
    }

    public void addToHistory(Email email) {
        String todayString = DateOperations.dateToString(new Date());
        if (!history.containsKey(todayString))
            history.put(todayString, new ArrayList<>());
        history.get(todayString).add(email);
    }

    public Map<String, List<Email>> getHistory() {
        return history;
    }
}
