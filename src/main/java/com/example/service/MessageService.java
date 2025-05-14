package com.example.service;

import org.springframework.stereotype.Service;

import com.example.entity.Message;


@Service
public class MessageService {


    public Message createMessage(Message message){
        return message;
    }
}
