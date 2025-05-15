package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties.Registration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.service.AccountService;
import com.example.service.MessageService;
import com.example.entity.Account;
import com.example.entity.Message;
import com.example.exception.DuplicateUsernameException;
import com.example.exception.RegistrationFailException;

import java.util.List;
import java.util.Optional;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@Controller
public class SocialMediaController {
    private AccountService accountService;
    private MessageService messageService;

    /**
     * Contructor for the account service and message service classes
     * @param accountService
     * @param messageService
     */
    @Autowired
    public SocialMediaController(AccountService accountService, MessageService messageService){
        this.accountService = accountService;
        this.messageService = messageService;
    }

    /**
     * Sends the account object without the id to the database for registration of a user
     * @param account
     * @return the registered user or null
     */
    @PostMapping("/register")
    public ResponseEntity<Account> postRegisterAccountHandler(@RequestBody Account account){
        try {
            Account newAccount = accountService.registerAccount(account); //null check?
            return ResponseEntity.status(200).body(newAccount);
        } catch (RegistrationFailException e) {
            return ResponseEntity.status(400).body(null);
        } catch (DuplicateUsernameException e) {
            return ResponseEntity.status(409).body(null);
        } 
    }

    /**
     * Sends the account object without the id to login the user
     * @param account
     * @return the logged in account or null
     */
    @PostMapping("/login")
    public ResponseEntity<Account> postLoginAccountHandler(@RequestBody Account account){
        Optional<Account> loggedAccount = accountService.loginAccount(account);
        if (!loggedAccount.isEmpty()){
            return ResponseEntity.status(200).body(account);
        } else {
            return ResponseEntity.status(401).body(null);
        }
    }

    /**
     * Sends the message object to create a message
     * @param message
     * @return the created message or null
     */
    @PostMapping("/messages")
    public ResponseEntity<Message> createMesssageHandler(@RequestBody Message message){
        Message createdMessage = messageService.createMessage(message);
        if (createdMessage != null){
            return ResponseEntity.status(200).body(createdMessage);
        } else {
            return ResponseEntity.status(400).body(null);
        }
    }

    /**
     * Sends the message object to delete a message
     * @param message
     * @return number of rows deleted (0 or 1)
     */
    @DeleteMapping("/messages/{message_id}")
    public ResponseEntity<Integer> deleteMessageByIdHandler(@RequestBody Message message){
        int rowsDeleted = messageService.deleteMessageById(message.getMessageId());
        return ResponseEntity.status(200).body(rowsDeleted);
    }

    /**
     * Sends message id from parameter and the message object to update the message
     * @param message_id
     * @param message
     * @return the updated message or null
     */
    @PatchMapping("/messages/{message_id}")
    public ResponseEntity<Integer> updateMessageByIdHandler(@PathVariable int message_id, @RequestBody Message message){
        int rowsUpdated = messageService.updateMessageById(message, message_id);
        if (rowsUpdated == 0){
            return ResponseEntity.status(400).body(null);
        } else {
            return ResponseEntity.status(200).body(rowsUpdated);
        }
    }

    /**
     * Retrieves a list of all the messages
     * @return a list of message objects
     */
    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getAllMessagesHandler(){
        List<Message> messages = messageService.getAllMessages();
        return ResponseEntity.status(200).body(messages);
    }

    /**
     * Sends the message id from parameter to get the message
     * @param message_id
     * @return the message object
     */
    @GetMapping("/messages/{message_id}")
    public ResponseEntity<Message> getOneMessageByMessageIdHandler(@PathVariable int message_id){
        Message message = messageService.getMessageById(message_id);
        return ResponseEntity.status(200).body(message);
        
    }

    /**
     * Sends account id from parameter to get a list of all messages with the account id
     * @param account_id
     * @return a list of message objects
     */
    @GetMapping("/accounts/{account_id}")
    public ResponseEntity<List<Message>> getAllMessagesByUserIdHandler(@PathVariable int account_id){
        List<Message> messages = messageService.getAllMessagesByAccountId(account_id);
        return ResponseEntity.status(200).body(messages);
        
    }
}
