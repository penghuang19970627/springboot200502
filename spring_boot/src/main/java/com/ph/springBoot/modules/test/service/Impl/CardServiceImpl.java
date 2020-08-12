package com.ph.springBoot.modules.test.service.Impl;

import com.ph.springBoot.modules.common.vo.Result;
import com.ph.springBoot.modules.test.entity.Card;
import com.ph.springBoot.modules.test.repository.CardRepository;
import com.ph.springBoot.modules.test.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private CardRepository cardRepository;

    /*jpa插入数据*/
    @Override
    @Transactional
    public Result<Card> insertCard(Card card){
        cardRepository.saveAndFlush(card);
        return new Result<Card>(Result.ResultStatus.SUCCESS.status,"Insert success.",card) ;
    }
}
