package org.example.cardsystem.controller;

import org.example.cardsystem.entity.Card;
import org.example.cardsystem.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Collections;
import java.util.List;

@Controller
public class CardController {

    @Autowired
    private CardService cardService;

    // 主界面
    @GetMapping("/card-management")
    public String cardManagement() {
        return "card-management";
    }

    // 添加名片
    @GetMapping("/card-management/add")
    public String addCardForm() {
        return "add-card";
    }

    @PostMapping("/add-card")
    public String saveCard(@RequestParam String cardName,
                           @RequestParam String phoneNumber,
                           @RequestParam String email,
                           @RequestParam String company,
                           @RequestParam String job,
                           @RequestParam String address,
                           HttpServletRequest request) {
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            // 如果用户未登录，重定向到登录页面
            return "redirect:/login";
        }
        cardService.saveCard(cardName, phoneNumber, email, company, job, address, userId);
        return "redirect:/card-management";
    }

    // 删除名片
    @GetMapping("/card-management/delete")
    public String deleteCardForm() {
        return "delete-card";
    }

    @PostMapping("/delete-card")
    public String deleteCard(@RequestParam Long cardId, HttpServletRequest request) {
        Long userId = (Long) request.getSession().getAttribute("userId");
        if (userId == null) {
            // 如果用户未登录，重定向到登录页面
            return "redirect:/login";
        }
        cardService.deleteCard(cardId, userId);
        return "redirect:/card-management";
    }

    // 修改名片
    @GetMapping("/card-management/edit")
    public String editCardForm() {
        return "edit-card";
    }

    @PostMapping("/edit-card")
    public String updateCard(@RequestParam Long cardId,
                             @RequestParam String cardName,
                             @RequestParam String phoneNumber,
                             @RequestParam String email,
                             @RequestParam String company,
                             @RequestParam String job,
                             @RequestParam String address,
                             HttpServletRequest request) {
        Long userId = (Long) request.getSession().getAttribute("userId");
        if (userId == null) {
            // 如果用户未登录，重定向到登录页面
            return "redirect:/login";
        }
        cardService.updateCard(cardId,cardName, phoneNumber, email, company, job, address, userId);
        return "redirect:/card-management";
    }

    // 查询名片
    @GetMapping("/card-management/query")
    public String queryCardForm(Model model) {
        // 默认不显示所有名片
        model.addAttribute("showAll", false);
        return "query-card";
    }

    @PostMapping("/query-card")
    public String searchCard(@RequestParam(name = "action", required = false) String action,
                             @RequestParam(name = "cardId", required = false) Long cardId,
                             HttpServletRequest request, Model model) {
        Long userId = (Long) request.getSession().getAttribute("userId");
        if (userId == null) {
            // 如果用户未登录，重定向到登录页面
            return "redirect:/login";
        }
        if ("searchById".equals(action)) {
            // 按ID查询
            Card card = cardService.searchCardById(cardId,userId);
            if (card != null) {
                model.addAttribute("cards", Collections.singletonList(card));
            } else {
                model.addAttribute("message", "未找到该ID的名片");
            }
        } else if ("searchAll".equals(action)) {
            // 查询所有
            List<Card> cards =cardService.searchAllCard(userId);
            System.out.println("Found cards: " + cards);  // 打印所有卡片
            model.addAttribute("cards", cards);
        }
        return "search-result";
    }


    // 修改密码
    @GetMapping("/card-management/password")
    public String changePasswordForm() {
        return "change-password";
    }

    @PostMapping("/update-password")
    public String updatePassword(@RequestParam String currentPassword,
                                 @RequestParam String newPassword,
                                 @RequestParam String confirmPassword,
                                 HttpServletRequest request) {
        Long userId = (Long) request.getSession().getAttribute("userId");
        if (userId == null) {
            // 如果用户未登录，重定向到登录页面
            return "redirect:/login";
        }
        cardService.updatePassword(userId, currentPassword, newPassword, confirmPassword);
        return "redirect:/card-management";
    }

    // 安全退出
    @GetMapping("/card-logout")
    public String cardLogout(HttpServletRequest request) {
        // 实现注销逻辑
        request.getSession().invalidate();
        return "redirect:/login"; // 假设有一个登录页面
    }
}