package com.library.springbootlibrary.controller;

import com.library.springbootlibrary.entity.Message;
import com.library.springbootlibrary.service.MessagesService;
import com.library.springbootlibrary.utils.ExtractJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/messages")
public class MessagesController {

    private MessagesService messagesService;
    @Autowired
    public MessagesController(MessagesService messagesService){
        this.messagesService = messagesService;
    }
//http://localhost:8080/api/messages/secure/add/message
    @PostMapping("/secure/add/message")
    public void postMessage(@RequestHeader(value = "Authorization") String token,
                            @RequestBody Message messageRequest){
        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
        messagesService.postMessage(messageRequest,userEmail);
    }

}
