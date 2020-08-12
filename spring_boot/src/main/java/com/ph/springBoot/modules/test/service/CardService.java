package com.ph.springBoot.modules.test.service;

import com.ph.springBoot.modules.common.vo.Result;
import com.ph.springBoot.modules.test.entity.Card;

public interface CardService {
    /*jpa添加数据*/
    Result<Card> insertCard(Card card);

}
