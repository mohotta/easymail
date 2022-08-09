package com.easymail.recipients.generators;

import com.easymail.customSupportLibraries.GetString;
import com.easymail.recipients.bluePrints.FriendRecipient;
import com.easymail.recipients.bluePrints.Recipient;

public class FriendRecipientGenerator extends RecipientGenerator{
    @Override
    protected Recipient recipientFactory(String name, String email) {
        System.out.print("Enter nickname (if not skip entering \"no nickname\"): ");
        String nickName = GetString.getNormalString();
        System.out.print("Enter birthday (yyyy/mm/dd): ");
        var birthdayFriend = GetString.getDate();
        return new FriendRecipient(name, email, nickName, birthdayFriend);
    }
}
