package io.learning.blogappspring.model;

import lombok.Data;

@Data
public class Payload {
    private String token;
    private String username;
    private Long id;

    public Payload(String token, String username, Long id) {
        this.token = token;
        this.username = username;
        this.id = id;
    }
}
