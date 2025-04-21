package com.workintech.fswebs18challengemaven.controller;

import com.workintech.fswebs18challengemaven.entity.Card;
import com.workintech.fswebs18challengemaven.exceptions.CardException;
import com.workintech.fswebs18challengemaven.repository.CardRepository;
import com.workintech.fswebs18challengemaven.util.CardValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CardController {
    private CardRepository cardRepository;

    @Autowired
    public CardController(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @GetMapping("/cards")
    public List<Card> findAll() {
        return cardRepository.findAll();
    }

    @GetMapping("/cards/byColor/{color}")
    public ResponseEntity<List<Card>> findByColor(@PathVariable String color) {
        List<Card> cards = cardRepository.findByColor(color);
        if (cards.isEmpty()) {
            throw new CardException("Card not found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(cards);
    }

    @GetMapping("/cards/byValue/{value}")
    public List<Card> findByValue(@PathVariable Integer value) {
        return cardRepository.findByValue(value);
    }

    @GetMapping("/cards/byType/{type}")
    public List<Card> findByType(@PathVariable String type) {
        return cardRepository.findByType(type);
    }

    @PostMapping("/cards")
    public ResponseEntity<Card> create(@RequestBody Card card) {
        CardValidation.validate(card);
        Card savedCard = cardRepository.save(card);
        return new ResponseEntity<>(savedCard, HttpStatus.OK);
    }

    @PutMapping("/cards/")
    public ResponseEntity<Card> updateCard(@RequestBody Card card) {
        Card updatedCard = cardRepository.update(card);
        return ResponseEntity.ok(updatedCard);
    }

    @DeleteMapping("/cards/{id}")
    public ResponseEntity<Card> remove(@PathVariable Long id) {
        Card updatedCard = cardRepository.remove(id);
        return ResponseEntity.ok(updatedCard);
    }
}
