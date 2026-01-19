package com.example.FlipCommerce.service;


import com.example.FlipCommerce.Enum.CardType;
import com.example.FlipCommerce.dto.RequestDto.CardRequestDto;
import com.example.FlipCommerce.dto.ResponseDto.CardResponseDto;
import com.example.FlipCommerce.exception.CustomerNotFoundException;
import com.example.FlipCommerce.model.Card;
import com.example.FlipCommerce.model.Customer;
import com.example.FlipCommerce.repository.CardRepository;
import com.example.FlipCommerce.repository.CustomerRepository;
import com.example.FlipCommerce.transformer.CardTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CardService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CardRepository cardRepository;

    public CardResponseDto addCard(CardRequestDto cardRequestDto) throws CustomerNotFoundException {

        Customer customer = customerRepository.findByEmailId(cardRequestDto.getEmailId());
        if(customer==null){
            throw new CustomerNotFoundException("Invalid email id!!!");
        }

        // dto -> entity
        Card card = CardTransformer.CardRequestDtoToCard(cardRequestDto);
        card.setCustomer(customer);
        customer.getCards().add(card);

        // save customer and card
        Customer savedCustomer = customerRepository.save(customer);

        // preapre response Dto
        return CardTransformer.CardToCardResponseDto(card);
    }

    public CardType getCardTypeWithMaxCount() {

        List<Card> cardList = cardRepository.findAll();

        Map<CardType, Integer> countMap = new HashMap<>();

        for (Card card : cardList) {
            CardType type = card.getCardType();
            countMap.put(type, countMap.getOrDefault(type, 0) + 1);
        }

        CardType maxType = null;
        int maxCount = 0;

        for (Map.Entry<CardType, Integer> entry : countMap.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                maxType = entry.getKey();
            }
        }

        return maxType;
    }

    public CardType getCardTypeWithMinCount() {

        List<Card> cardList = cardRepository.findAll();

        Map<CardType, Integer> countMap = new HashMap<>();

        for (Card card : cardList) {
            CardType type = card.getCardType();
            countMap.put(type, countMap.getOrDefault(type, 0) + 1);
        }

        CardType minType = null;
        int minCount = Integer.MAX_VALUE;

        for (Map.Entry<CardType, Integer> entry : countMap.entrySet()) {
            if (entry.getValue() < minCount) {
                minCount = entry.getValue();
                minType = entry.getKey();
            }
        }

        return minType;
    }

}
