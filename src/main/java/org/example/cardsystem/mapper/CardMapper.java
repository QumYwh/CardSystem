package org.example.cardsystem.mapper;

import org.apache.ibatis.annotations.*;
import org.example.cardsystem.entity.Card;
import java.util.List;

@Mapper
public interface CardMapper {

    // 查询所有属于当前用户的名片
    @Select("SELECT * FROM card WHERE user_id = #{userId}")
    List<Card> selectAllCardsByUserId(Long userId);
    @Select("SELECT * FROM card WHERE card_id = #{cardId} AND user_id = #{userId}")
    Card selectCardByIdAndUserId(@Param("cardId") Long cardId, @Param("userId") Long userId);
    // 插入新的名片，同时设置 user_id
    @Insert("INSERT INTO card (cardName, phonenumber, email, company, job, address, user_id) " +
            "VALUES (#{card.cardName}, #{card.phonenumber}, #{card.email}, #{card.company}, #{card.job}, #{card.address}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "card.cardId")
    void insertCard(@Param("card") Card card, @Param("userId") Long userId);

    // 更新属于当前用户的名片
    @Update("UPDATE card " +
            "SET cardName = #{card.cardName}, " +
            "phonenumber = #{card.phonenumber}, " +
            "email = #{card.email}, " +
            "company = #{card.company}, " +
            "job = #{card.job}, " +
            "address = #{card.address} " +
            "WHERE card_id = #{cardId} AND user_id = #{userId}")
    void updateCard(@Param("card") Card card, @Param("userId") Long userId,@Param("cardId") Long cardId);

    // 删除属于当前用户的名片
    @Delete("DELETE FROM card WHERE card_id = #{cardId} AND user_id = #{userId}")
    void deleteCard(@Param("cardId") Long cardId, @Param("userId") Long userId);

    @Update("UPDATE user SET password = #{newPassword} WHERE id = #{userId}")
    void updatePassword(Long userId, String currentPassword, String newPassword, String confirmPassword);
}