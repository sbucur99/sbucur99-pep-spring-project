package com.example.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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

import java.sql.SQLException;
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
    public SocialMediaController(AccountService accountService, MessageService messageService){
        this.accountService = accountService;
        this.messageService = messageService;
    }

    /**
     * Sends the account object without the id to the database for registration of a user
     * @param account
     * @return
     */
    @PostMapping("/register")
    public ResponseEntity<Account> postRegisterAccountHandler(@RequestBody Account account){
        Account newAccount = accountService.registerAccount(account);
        if (newAccount != null){
            return ResponseEntity.status(200).body(newAccount);
        } else {
            return ResponseEntity.status(400).body(null);
        }
    }

    /**
     * Sends the account object without the id to login the user
     * @param account
     * @return
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
     * 
     * @param message
     * @return
     */
    @DeleteMapping("/messages/{message_id}")
    public ResponseEntity<Message> postMessageHandler(@RequestBody Message message){
        Message newMessage = messageService.deleteMessageById(message.getMessageId());
        if (newMessage != null){
            return ResponseEntity.status(200).body(newMessage);
        } else {
            return ResponseEntity.status(400).body(null);
        }
    }

    /**
     * 
     * @param message_id
     * @param message
     * @return
     */
    @PutMapping("/messages/{message_id}")
    public ResponseEntity<Message> updateMessageByIdHandler(@PathVariable int message_id, @RequestBody Message message){
        Message newMessage = messageService.updateMessageById(message, message_id);
        if (newMessage != null){
            return ResponseEntity.status(200).body(newMessage);
        } else {
            return ResponseEntity.status(400).body(null);
        }
    }

    /**
     * 
     * @return
     */
    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getAllMessagesHandler(){
        List<Message> messages = messageService.getAllMessages();
        return ResponseEntity.status(200).body(messages);
    }

    /**
     * 
     * @param message_id
     * @return
     */
    @GetMapping("/messages/{message_id}")
    public ResponseEntity<Message> getOneMessageByMessageIdHandler(@PathVariable int message_id){
        Message message = messageService.getMessageById(message_id);
        return ResponseEntity.status(200).body(message);
        
    }

    /**
     * 
     * @param account_id
     * @return
     */
    @GetMapping("/accounts/{account_id}")
    public ResponseEntity<List<Message>> getAllMessagesByUserIdHandler(@PathVariable int account_id){
        List<Message> messages = messageService.getAllMessagesByAccountId(account_id);
        return ResponseEntity.status(200).body(messages);
        
    }
}
