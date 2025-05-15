package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.exception.DuplicateUsernameException;
import com.example.exception.RegistrationFailException;
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
     * Registers a user account if it doesn't already exist and password is at least 4 characters
     * @param account
     * @return an Account object wrapped inside an Optional or an empty Optional
     */
    public Account registerAccount(Account account){
        if (account.getUsername() == null || account.getUsername().isEmpty() || account.getPassword() == null || account.getPassword().length() < 4) {
            throw new RegistrationFailException("Failed to register!");
        }
        Optional<Account> accountFind = accountRepository.findByUsername(account.getUsername());
        if (accountFind.isPresent()) {
            throw new DuplicateUsernameException("Duplicate username found!");
        }
        Account persistedAccount = accountRepository.save(account);
        return persistedAccount;
    }

    /**
     * Checks whether account exists with username and corresponding password
     * @param account
     * @return an Account object wrapped inside an Optional or an empty Optional
     */
    public Optional<Account> loginAccount(Account account){
        return accountRepository.findByUsernameAndPassword(account.getUsername(), account.getPassword());
    }
}
