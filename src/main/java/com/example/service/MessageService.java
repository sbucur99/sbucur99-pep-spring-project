package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.repository.MessageRepository;

import java.util.List;

import java.util.ArrayList;

import java.util.Optional;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    /**
     * Constructor for the message repository interface
     * @param messageRepository
     */
    @Autowired
    public MessageService(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }

    /**
     * Creates a message with message object if the id doesn't already exist, the message it is not blank and within 255 characters
     * @param message
     * @return the Message created
     */
    public Message createMessage(Message message){
        if (message.getMessageText() == null || message.getMessageText().isBlank() || message.getMessageText().length() > 255 
        || !messageRepository.existsById(message.getPostedBy())) {
            return null;
        }
        Message persistedMessage = messageRepository.save(message);
        return persistedMessage;
    }

    /**
     * Updates a message by the message id if id exists, the message is not empty and within 255 characters
     * @param message
     * @param message_id
     * @return the number of rows updated
     */
    public int updateMessageById(Message message, int message_id){
        if (message.getMessageText() == null || message.getMessageText().isEmpty() || message.getMessageText().length() > 255){
            return 0;
        }
        Optional<Message> messageFind = messageRepository.findById(message_id);
        if (messageFind.isEmpty()){
            return 0;
        }
        Message updatedMessage = messageFind.get();
        updatedMessage.setMessageText(message.getMessageText());
        messageRepository.save(updatedMessage);
        return 1;
    }

    /**
     * Deletes a message by the id
     * @param message_id
     * @return the number of rows deleted
     */
    public int deleteMessageById(int message_id){
        Optional<Message> messageFind = messageRepository.findById(message_id);
        if (messageFind.isEmpty()){
            return 0;
        }
        return 1;
    }

    /**
     * Retrieves a list of message objects
     * @return a list of message objects
     */
    public List<Message> getAllMessages(){
        List<Message> messages = new ArrayList<>(); 
        messages = messageRepository.findAll();
        return messages;
    }

    /**
     * Retrieves a message by message id
     * @param message_id
     * @return a message object or null
     */
    public Message getMessageById(int message_id){
        Optional<Message> messageFind = messageRepository.findById(message_id);
        if (messageFind.isEmpty()){
            return null;
        }
        return messageFind.get();
    }

    /**
     * Retrieves a list of messages by account id
     * @param account_id
     * @return a list of message objects
     */
    public List<Message> getAllMessagesByPostedBy(int account_id){
        List<Message> messages = new ArrayList<>();
        messages = messageRepository.findAllByPostedBy(account_id);
        return messages;
    }
}
