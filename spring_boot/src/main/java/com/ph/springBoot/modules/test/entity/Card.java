package com.ph.springBoot.modules.test.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "p_card")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cardId;
    @Column(name = "card_no",length = 33,unique = true)
    private String cardNo;

    /*一对一*/
    @OneToOne(mappedBy = "studentCard" ,cascade = CascadeType.REFRESH,fetch = FetchType.LAZY)
    @JsonIgnore
    private Student student;

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
