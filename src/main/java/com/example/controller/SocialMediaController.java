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

    public SocialMediaController(AccountService accountService, MessageService messageService){
        this.accountService = accountService;
        this.messageService = messageService;
    }

    /**
     * Sends the account object without the id to the database for registration of a user
     * @param account
     * @return
     * @throws SQLException
     */
    @PostMapping("/register")
    public ResponseEntity<Account> postRegisterAccountHandler(@RequestBody Account account) throws SQLException{
        Account newAccount = accountService.registerAccount(account);
        if (newAccount != null){
            return ResponseEntity.status(200).body(account);
        } else {
            return ResponseEntity.status(400).body(null);
        }
    }

    /**
     * Sends the account object without the id to login the user
     * @param account
     * @return
     * @throws SQLException
     */
    @PostMapping("/login")
    public ResponseEntity<Account> postLoginAccountHandler(@RequestBody Account account) throws SQLException{
        Account loggedAccount = accountService.loginAccount(account);
        if (loggedAccount != null){
            return ResponseEntity.status(200).body(account);
        } else {
            return ResponseEntity.status(401).body(null);
        }
    }

    @DeleteMapping("/messages/{message_id}")
    public ResponseEntity<Message> postMessageHandler(@RequestBody Message message) throws SQLException{
        Message newMessage = messageService.createMessage(message);
        if (newMessage != null){
            return ResponseEntity.status(200).body(newMessage);
        } else {
            return ResponseEntity.status(400).body(null);
        }
    }

    @PutMapping("/messages/{message_id}")
    public ResponseEntity<Message> updateMessageByIdHandler(@PathVariable int message_id, @RequestBody Message message) throws SQLException{
        Message newMessage = messageService.updateMessage(message, message_id);
        if (newMessage != null){
            return ResponseEntity.status(200).body(newMessage);
        } else {
            return ResponseEntity.status(400).body(null);
        }
    }

    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getAllMessagesHandler() throws SQLException{
        List<Message> messages = messageService.getAllMessages();
        return ResponseEntity.status(200).body(messages);
    }

    @GetMapping("/messages/{message_id}")
    public ResponseEntity<Message> getOneMessageByMessageIdHandler(@PathVariable int message_id) throws SQLException{
        Message message = messageService.getMessageById(message_id);
        return ResponseEntity.status(200).body(message);
        
    }

    @GetMapping("/accounts/{account_id}")
    public ResponseEntity<List<Message>> getAllMessagesByUserIdHandler(@PathVariable int account_id) throws SQLException{
        List<Message> messages = messageService.getAllMessagesByAccountId(account_id);
        return ResponseEntity.status(200).body(messages);
        
    }
}
