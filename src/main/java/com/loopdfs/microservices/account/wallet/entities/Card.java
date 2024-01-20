package com.loopdfs.microservices.account.wallet.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id", updatable = false, nullable = false)
    private Long cardId;
    @Column(name = "card_alias")
    private String cardAlias;
    @ManyToOne
    @JoinColumn(name = "account_id", updatable = false, nullable = false)
    private Account account;
    @Column(name = "card_type", updatable = false, nullable = false)
    private String cardType;
}
