package org.example.cardsystem.service;

import org.example.cardsystem.entity.User;

public interface UserService {
    User login(String username, String password);
    boolean register(User user) ;
}
