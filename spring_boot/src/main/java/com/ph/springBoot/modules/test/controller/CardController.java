package com.ph.springBoot.modules.test.controller;

import com.ph.springBoot.modules.common.vo.Result;
import com.ph.springBoot.modules.test.entity.Card;
import com.ph.springBoot.modules.test.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ph")
public class CardController {

    @Autowired
    private CardService cardService;

    /*jpa插入数据*/
    /*127.0.0.1/ph/card*/
    /*"cardNo":"ass11"}*/
    @PostMapping(value = "/card" ,consumes = "application/json")
    public Result<Card> insertCard(@RequestBody Card card){
        return cardService.insertCard(card);
    }
}
