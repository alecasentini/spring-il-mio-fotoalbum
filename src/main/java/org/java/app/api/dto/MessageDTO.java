package org.java.app.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class MessageDTO {

    private int id;

    @NotBlank(message = "L'email è obbligatoria")
    @Email(message = "L'email deve essere valida")
    private String email;

    @NotBlank(message = "Il contenuto è obbligatorio")
    private String content;

    public MessageDTO() { }

    public MessageDTO(String email, String content) {
        setEmail(email);
        setContent(content);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "[" + getId() + "] Messaggio:\n"
                + "email: " + getEmail() + "\n"
                + "contenuto: " + getContent() + "\n";
    }
}