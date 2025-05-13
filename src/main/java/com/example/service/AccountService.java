package com.example.service;

import org.springframework.stereotype.Service;

import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;

import service.AccountService;
@Service
public class AccountService {
    private AccountRepository accountRepository;
    private MessageRepository messageRepository;

    public setAccountRepository(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }
}
