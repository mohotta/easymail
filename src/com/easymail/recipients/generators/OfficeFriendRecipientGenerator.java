package com.easymail.recipients.generators;

import com.easymail.customSupportLibraries.GetString;
import com.easymail.recipients.bluePrints.OfficeFriendRecipient;
import com.easymail.recipients.bluePrints.Recipient;

public class OfficeFriendRecipientGenerator extends RecipientGenerator{
    @Override
    protected Recipient recipientFactory(String name, String email) {
        System.out.print("Enter designation: ");
        String designationOffFriend = GetString.getNormalString();
        System.out.print("Enter birthday (yyyy/mm/dd): ");
        var birthdayOffFriend = GetString.getDate();
        return new OfficeFriendRecipient(name, email, designationOffFriend, birthdayOffFriend);
    }
}
