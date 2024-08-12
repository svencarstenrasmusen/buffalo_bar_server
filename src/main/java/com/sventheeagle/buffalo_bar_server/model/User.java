package com.sventheeagle.buffalo_bar_server.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class User {
    @Id
    private String id;

    private String username;
    private String email;
    private Date createdAt;
}
