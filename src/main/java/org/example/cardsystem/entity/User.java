package org.example.cardsystem.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class User {
    private Long id;
    private String username;
    private String password;

    // 构造函数
    public User() {}

}

