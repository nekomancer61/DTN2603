import java.util.Date;

import models.Account;

public class Exercise2_DefaultValue {

    public Exercise2_DefaultValue() {
    }
    
    public void question1(){
        Account[] accounts = new Account[5];
        for (int i = 0; i < accounts.length; i++) {
            accounts[i] = new Account(i, "Email "+ ++i, "User name "+ ++i, "Full name" + ++i, null, null, new Date());
            System.out.println(accounts[i].toString());
        }
    }
}
