package org.example.cardsystem.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class Card {

    private Long cardId;
    private String cardName;
    private String phonenumber;
    private String email;
    private String company;
    private String job;
    private String address;
    private Long userId;
    public Card() {}
}
