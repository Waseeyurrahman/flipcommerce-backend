package com.example.FlipCommerce.controller;

import com.example.FlipCommerce.Enum.Category;
import com.example.FlipCommerce.dto.RequestDto.SellerRequestDto;
import com.example.FlipCommerce.dto.ResponseDto.ProductResponseDto;
import com.example.FlipCommerce.dto.ResponseDto.SellerResponseDto;
import com.example.FlipCommerce.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    SellerService sellerService;

    @PostMapping("/add")
    public ResponseEntity addSeller(@RequestBody SellerRequestDto sellerRequestDto){

        SellerResponseDto sellerResponseDto = sellerService.addSeller(sellerRequestDto);
        return new ResponseEntity(sellerResponseDto, HttpStatus.CREATED);
    }

    // update the seller info based on email.
    @PutMapping("/update")
    public ResponseEntity<SellerResponseDto> updateSeller(
            @RequestParam String emailId,
            @RequestBody SellerRequestDto sellerRequestDto) {

        return new ResponseEntity<>(
                sellerService.updateSellerByEmail(emailId, sellerRequestDto),
                HttpStatus.OK
        );
    }

    // get all the sellers who sell products of a particular category
    @GetMapping("/category/{category}")
    public ResponseEntity<List<SellerResponseDto>> getSellersByCategory(
            @PathVariable Category category) {

        return new ResponseEntity<>(
                sellerService.getSellersByCategory(category),
                HttpStatus.OK
        );
    }

    // get all the products sold by a seller in a category
    @GetMapping("/products")
    public ResponseEntity<List<ProductResponseDto>> getProductsBySellerAndCategory(
            @RequestParam String emailId,
            @RequestParam Category category) {

        return new ResponseEntity<>(
                sellerService.getProductsBySellerAndCategory(emailId, category),
                HttpStatus.OK
        );
    }

    // seller with highest number of products
    @GetMapping("/max-products")
    public ResponseEntity<SellerResponseDto> sellerWithMaxProducts() {

        return new ResponseEntity<>(
                sellerService.sellerWithMaxProducts(),
                HttpStatus.OK
        );
    }


    // seller with minimum number of products
    @GetMapping("/min-products")
    public ResponseEntity<SellerResponseDto> sellerWithMinProducts() {

        return new ResponseEntity<>(
                sellerService.sellerWithMinProducts(),
                HttpStatus.OK
        );
    }


}
