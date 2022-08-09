package com.easymail.recipients.generators;

import com.easymail.customSupportLibraries.GetString;
import com.easymail.recipients.bluePrints.OfficialRecipient;
import com.easymail.recipients.bluePrints.Recipient;

public class OfficialRecipientGenerator extends RecipientGenerator{
    @Override
    protected Recipient recipientFactory(String name, String email) {
        System.out.print("Enter designation: ");
        var designationOfficial = GetString.getNormalString();
        return new OfficialRecipient(name, email, designationOfficial);
    }
}
