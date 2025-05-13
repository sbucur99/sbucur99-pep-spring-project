package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.service.AccountService;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@Controller
//@RequestMapping("/")
public class SocialMediaController {
    private AccountService accountService;

    public SocialMediaController(AccountService accountService){
        this.accountService = accountService;
    }

    //@GetMapping("/a")
    //@RequestMapping("", method = RequestMapping.GET)
    public @ResponseBody registerAccount(@RequestBody Account account){
        
    }

}
