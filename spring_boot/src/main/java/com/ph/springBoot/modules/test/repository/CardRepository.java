package com.ph.springBoot.modules.test.repository;

import com.ph.springBoot.modules.test.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card,Integer> {
}
