package org.example.cardsystem.service;

import org.example.cardsystem.entity.Card;

import java.util.List;

public interface CardService {
    void saveCard(String cardName, String phoneNumber, String email, String company, String job, String address, Long userId);
    void deleteCard(Long cardId,Long userId);
    void updateCard(Long cardId,String cardName, String phoneNumber, String email, String company, String job, String address, Long userId);
    Card searchCardById(Long cardId, Long userId);
    List<Card> searchAllCard(Long userId);
    void updatePassword(Long userId,String currentPassword, String newPassword, String confirmPassword);
}