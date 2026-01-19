package com.example.FlipCommerce.controller;

import com.example.FlipCommerce.Enum.CardType;
import com.example.FlipCommerce.dto.RequestDto.CardRequestDto;
import com.example.FlipCommerce.dto.ResponseDto.CardResponseDto;
import com.example.FlipCommerce.exception.CustomerNotFoundException;
import com.example.FlipCommerce.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/card")
public class CardController {

    @Autowired
    CardService cardService;

    @PostMapping("/add")
    public ResponseEntity addCard(@RequestBody CardRequestDto cardRequestDto){

        try{
            CardResponseDto cardResponseDto = cardService.addCard(cardRequestDto);
            return new ResponseEntity(cardResponseDto, HttpStatus.CREATED);
        }
        catch (CustomerNotFoundException e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    // tell me the card type which exists max number of times.
    @GetMapping("/max-card-type")
    public ResponseEntity<CardType> getCardTypeWithMaxCount() {
        return new ResponseEntity<>(cardService.getCardTypeWithMaxCount(), HttpStatus.OK);
    }


    // tell me the card type which exists min number of times.
    @GetMapping("/min-card-type")
    public ResponseEntity<CardType> getCardTypeWithMinCount() {
        return new ResponseEntity<>(cardService.getCardTypeWithMinCount(), HttpStatus.OK);
    }
}
