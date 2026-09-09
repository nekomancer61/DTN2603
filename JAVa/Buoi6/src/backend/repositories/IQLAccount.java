package backend.repositories;

import java.util.List;

import models.Account;
import models.ConnectionDetail;

public interface IQLAccount {
    List<Account> displayAccount(ConnectionDetail detail);
}
