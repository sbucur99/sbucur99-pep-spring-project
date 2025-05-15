package com.example.service;

import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.repository.MessageRepository;

import java.util.List;

import java.util.ArrayList;

import java.util.Optional;

@Service
public class MessageService {

    private MessageRepository messageRepository;

    /**
     * Constructor for the message repository interface
     * @param messageRepository
     */
    public MessageService(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }

    /**
     * Creates a message with message object if the id doesn't already exist, the message it is not blank and within 255 characters
     * @param message
     * @return the Message created
     */
    public Message createMessage(Message message){
        if (message.getMessageText() == null || message.getMessageText().isBlank() || message.getMessageText().length() >= 255 
        || (message.getMessageId() != null && !messageRepository.existsById(message.getMessageId()))) {
            return null;
        }
        Message persistedMessage = messageRepository.save(message);
        return persistedMessage;
    }

    /**
     * Updates a message by the message id if id exists, the message is not empty and within 255 characters
     * @param message
     * @param message_id
     * @return an updated message or null
     */
    public Message updateMessageById(Message message, int message_id){
        if (message.getMessageText() == null || message.getMessageText().isEmpty() || message.getMessageText().length() > 255){
            return null;
        }
        Optional<Message> messageFind = messageRepository.findById(message_id);
        if (messageFind.isEmpty()){
            return null;
        }
        Message updatedMessage = messageFind.get();

        updatedMessage.setMessageText(message.getMessageText());
        return messageRepository.save(updatedMessage);
    }

    /**
     * Deletes a message by the id
     * @param message_id
     * @return a deleted message object or null
     */
    public Message deleteMessageById(int message_id){
        Optional<Message> messageFind = messageRepository.findById(message_id);
        if (messageFind.isEmpty()){
            return null;
        }
        Message message = messageFind.get();
        messageRepository.deleteById(message_id);
        return message;
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
    public List<Message> getAllMessagesByAccountId(int account_id){
        List<Message> messages = new ArrayList<>();
        messages = messageRepository.findAllByAccountId(account_id);
        return messages;
    }
}
