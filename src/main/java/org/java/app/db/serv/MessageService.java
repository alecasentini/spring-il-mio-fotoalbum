package org.java.app.db.serv;

import org.java.app.db.pojo.Message;
import org.java.app.db.repo.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    @Autowired
    private MessageRepo messageRepo;

    public Message save(Message message) {
        return messageRepo.save(message);
    }

    public List<Message> findAll() {
        return messageRepo.findAll();
    }

    public Optional<Message> findById(int id) {
        return messageRepo.findById(id);
    }

    public void deleteById(int id) {
        messageRepo.deleteById(id);
    }
}