package com.loopdfs.microservices.account.wallet.service;

import com.loopdfs.microservices.account.wallet.entities.Card;

import java.util.List;
import java.util.Map;

public interface CardService {
    List<Card> getAllCards();

    List<Map<String, Object>> getCardsByAccountId(Long accountId);

    Card createCard(Card card);

    Card updateCard(Long cardId, Card updatedCard);

    void deleteCard(Long cardId);
}
