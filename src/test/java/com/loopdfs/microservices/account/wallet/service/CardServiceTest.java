package com.loopdfs.microservices.account.wallet.service;

import com.loopdfs.microservices.account.wallet.entities.Card;
import com.loopdfs.microservices.account.wallet.repositories.CardRepository;
import com.loopdfs.microservices.account.wallet.service.CardService;
import com.loopdfs.microservices.account.wallet.service.impl.CardServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import static org.mockito.Mockito.*;

public class CardServiceTest {
    @Mock
    private CardRepository cardRepository;
    @InjectMocks
    private CardService cardService = new CardServiceImpl(cardRepository);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllCards() {
        when(cardRepository.findAll()).thenReturn(Arrays.asList(new Card(), new Card()));
        List<Card> accounts = (cardService.getAllCards());
        Assertions.assertTrue(accounts.size() > 0);
        verify(cardRepository, times(1)).findAll();
    }

    @Test
    void getCardsAccountId() {
        Long accountId = 2L;
        when(cardRepository.findByAccount_AccountId(accountId)).thenReturn(Arrays.asList(new Card(), new Card()));
        List<Map<String, Object>> cards = cardService.getCardsByAccountId(accountId);

        Assertions.assertTrue(!cards.isEmpty());
        verify(cardRepository, times(1)).findByAccount_AccountId(accountId);
    }

    @Test
    void createCard() {
        Card card = new Card();
        card.setCardAlias("Alias64dud");
        card.setCardType("Virtual");
        when(cardRepository.save(card)).thenReturn(card);
        Card createdCard = cardService.createCard(card);
        verify(cardRepository, times(1)).save(createdCard);
        Assertions.assertEquals("Alias64dud", createdCard.getCardAlias());
    }

    @Test
    void updateCard() {
        Long cardId = 1L;
        Card card = new Card();
        card.setCardAlias("UpdatedIban");
        when(cardRepository.findById(cardId)).thenReturn(Optional.of(new Card()));
        when(cardRepository.save(any(Card.class))).thenAnswer(invocation -> invocation.getArgument(0));
        Card updatedCard = cardService.updateCard(cardId, card);

        verify(cardRepository, times(1)).findById(cardId);
        verify(cardRepository, times(1)).save(updatedCard);
        Assertions.assertNotNull(updatedCard);
    }

    @Test
    void deleteCard() {
        Long accountId = 1L;
        cardService.deleteCard(accountId);
        verify(cardRepository, times(1)).deleteById(accountId);
    }
}
