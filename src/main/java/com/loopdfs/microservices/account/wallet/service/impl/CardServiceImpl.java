package com.loopdfs.microservices.account.wallet.service.impl;

import com.loopdfs.microservices.account.wallet.entities.Card;
import com.loopdfs.microservices.account.wallet.exception.CustomException;
import com.loopdfs.microservices.account.wallet.repositories.CardRepository;
import com.loopdfs.microservices.account.wallet.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.loopdfs.microservices.account.wallet.utils.Constants.*;

import java.util.*;

@Service
public class CardServiceImpl implements CardService {
    @Autowired
    private  CardRepository cardRepository;

    public CardServiceImpl(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }

    @Override
    public List<Map<String, Object>> getCardsByAccountId(Long accountId) {
        List<Card> cards = cardRepository.findByAccount_AccountId(accountId);

        if (cards.isEmpty()) {
            throw new CustomException(NOT_FOUND, NOT_FOUND_MESSAGE);
        }

        List<Map<String, Object>> responseList = new ArrayList<>();

        for (Card card : cards) {
            Map<String, Object> cardDetails = new HashMap<>();
            cardDetails.put("cardId", card.getCardId());
            cardDetails.put("cardAlias", card.getCardAlias());
            cardDetails.put("cardType", card.getCardType());
            responseList.add(cardDetails);
        }

        return responseList;
    }

    @Override
    public Card createCard(Card card) {
        return cardRepository.save(card);
    }

    @Override
    public Card updateCard(Long cardId, Card updatedCard) {
        Optional<Card> existingCardOptional = cardRepository.findById(cardId);
        if (!existingCardOptional.isPresent()) {
            throw new CustomException(NOT_FOUND, NOT_FOUND_MESSAGE);
        } else {
            Card existingCard = existingCardOptional.get();
            existingCard.setCardAlias(updatedCard.getCardAlias());

            return cardRepository.save(existingCard);
        }
    }

    @Override
    public void deleteCard(Long cardId) {
        cardRepository.deleteById(cardId);
    }
}
