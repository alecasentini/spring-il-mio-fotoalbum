package org.java.app.api;

import org.java.app.api.dto.MessageDTO;
import org.java.app.db.pojo.Message;
import org.java.app.db.serv.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/v1.0")
public class MessageRestController {

    @Autowired
    private MessageService messageService;

    @PostMapping("/messages")
    public ResponseEntity<Message> saveMessage(@RequestBody MessageDTO messageDto) {
        Message message = new Message();
        message.setEmail(messageDto.getEmail());
        message.setContent(messageDto.getContent());
        message = messageService.save(message);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }
}