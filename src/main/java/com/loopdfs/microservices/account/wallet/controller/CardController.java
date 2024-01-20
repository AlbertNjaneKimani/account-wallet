package com.loopdfs.microservices.account.wallet.controller;

import com.loopdfs.microservices.account.wallet.entities.Card;
import com.loopdfs.microservices.account.wallet.exception.CustomException;
import com.loopdfs.microservices.account.wallet.service.CardService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1")
public class CardController {
    @Autowired
    private CardService cardService;

    @Operation(summary = "Get list of all cards")
    @GetMapping("/cards")
    public ResponseEntity<List<Card>> getAllCards() {
        List<Card> cards = cardService.getAllCards();
        return new ResponseEntity<>(cards, HttpStatus.OK);
    }
    @Operation(summary = "Get list of all cards for a particular account")
    @GetMapping("cards/{accountId}")
    public List<Map<String, Object>> getCardAccountId(@PathVariable Long accountId) {
        return cardService.getCardsByAccountId(accountId);
    }
    @Operation(summary = "Create a new cards")
    @PostMapping("/cards")
    public ResponseEntity<Card> createCard(@RequestBody Card card) {
        Card createdAccount = cardService.createCard(card);
        return new ResponseEntity<>(createdAccount, HttpStatus.CREATED);
    }
    @Operation(summary = "update a card")
    @PutMapping("/cards/{cardId}")
    public ResponseEntity<Card> updateCard(@PathVariable Long cardId,
                                           @RequestBody Card card) {
        try {
            Card updated = cardService.updateCard(cardId, card);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (CustomException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @Operation(summary = "Delete a card")
    @DeleteMapping("/cards/{cardId}")
    public ResponseEntity<Void> deleteCard(@PathVariable Long cardId) {
        cardService.deleteCard(cardId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
