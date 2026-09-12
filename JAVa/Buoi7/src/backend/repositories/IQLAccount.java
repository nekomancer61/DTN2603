package backend.repositories;

import java.util.List;

import models.Account;

public interface IQLAccount {
    List<Account> displayAccount();
    void insertAccount(int accountId, String email, String userName, String fullName);
    void updateAccount(int accountId, Account newAccount);
    void deleteAccount(int accountId);
}
