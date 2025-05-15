package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;

import java.util.Optional;

@Service
public class AccountService {
    private AccountRepository accountRepository;

    /**
     * Constructor for the message repository interface
     */
    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    /**
     * 
     * @param account
     * @return
     */
    public Account registerAccount(Account account){
        if (account.getUsername() == null || account.getUsername().isEmpty() || account.getPassword() == null || account.getPassword().length() < 4) {
            return null;
        }
        Account persistedAccount = accountRepository.registerAccount(account);
        return persistedAccount;
    }

    /**
     * 
     * @param account
     * @return
     */
    Optional<Account> loginAccount(Account account){
        return accountRepository.findByUsernameAndPassword(account.getUsername());
    }
    


}
