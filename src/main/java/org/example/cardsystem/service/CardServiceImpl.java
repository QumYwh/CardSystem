package org.example.cardsystem.service;

import org.example.cardsystem.mapper.CardMapper;
import org.example.cardsystem.entity.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private CardMapper cardMapper;

    public void saveCard(String cardName, String phoneNumber, String email, String company, String job, String address, Long userId) {
        Card card1 = new Card();
        card1.setCardName(cardName);
        card1.setPhonenumber(phoneNumber);
        card1.setEmail(email);
        card1.setCompany(company);
        card1.setJob(job);
        card1.setAddress(address);
        card1.setUserId(userId);
        cardMapper.insertCard(card1,userId);
        // 打印插入后的Card对象，确认cardId是否被正确设置
        System.out.println("Inserted Card: " + card1);
    }

    public void deleteCard(Long cardId,Long userId) {
        cardMapper.deleteCard(cardId,userId);
    }

    public void updateCard(Long cardId,String cardName, String phoneNumber, String email, String company, String job, String address, Long userId) {
        Card card2 = new Card();
        card2.setCardName(cardName);
        card2.setPhonenumber(phoneNumber);
        card2.setEmail(email);
        card2.setCompany(company);
        card2.setJob(job);
        card2.setAddress(address);
        card2.setUserId(userId);
        cardMapper.updateCard(card2,userId,cardId);
    }

    public Card searchCardById(Long cardId, Long userId) {
        return cardMapper.selectCardByIdAndUserId(cardId,userId);
    }

    public void updatePassword(Long userId, String currentPassword, String newPassword, String confirmPassword) {
        cardMapper.updatePassword(userId, currentPassword,newPassword, confirmPassword);
    }
    public List<Card> searchAllCard(Long userId){
        return cardMapper.selectAllCardsByUserId(userId);
    }

}