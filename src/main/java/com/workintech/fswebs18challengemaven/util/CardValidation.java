package com.workintech.fswebs18challengemaven.util;

import com.workintech.fswebs18challengemaven.entity.Card;
import com.workintech.fswebs18challengemaven.entity.Type;
import com.workintech.fswebs18challengemaven.exceptions.CardException;
import org.springframework.http.HttpStatus;

public class CardValidation {
    public static void validate(Card card) {
        if (card.getType() != null && card.getValue() != null)
            throw new CardException("Hem value hem type olamaz", HttpStatus.BAD_REQUEST);
        if (card.getType() == null && card.getValue() == null)
            throw new CardException("Hem value hem type null olamaz", HttpStatus.BAD_REQUEST);
        if (card.getType() == Type.JOKER) {
            if (card.getColor() != null || card.getValue() != null)
                throw new CardException("JOKER kartı için color ve value null olmalı", HttpStatus.BAD_REQUEST);
        }
    }
}
