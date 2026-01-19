package com.example.FlipCommerce.controller;

import com.example.FlipCommerce.Enum.Category;
import com.example.FlipCommerce.dto.RequestDto.ProductRequestDto;
import com.example.FlipCommerce.dto.ResponseDto.ProductResponseDto;
import com.example.FlipCommerce.exception.SellerNotFoundException;
import com.example.FlipCommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/add")
    public ResponseEntity addProduct(@RequestBody ProductRequestDto productRequestDto){

        try{
            ProductResponseDto productResponseDto = productService.addProduct(productRequestDto);
            return new ResponseEntity(productResponseDto, HttpStatus.CREATED);
        }
        catch (SellerNotFoundException e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get/category/{category}/price/{price}")
    public ResponseEntity getAllProductsByCategoryAndPrice(@PathVariable("category") Category category,
                                                           @PathVariable("price") int price){

        List<ProductResponseDto> productResponseDtos = productService.getAllProductsByCategoryAndPrice(category,price);
        return new ResponseEntity(productResponseDtos,HttpStatus.FOUND);
    }

    // get all the products of a category
    @GetMapping("/get/category/{category}")
    public ResponseEntity<List<ProductResponseDto>> getAllProductsByCategory(
            @PathVariable Category category) {

        return new ResponseEntity<>(
                productService.getAllProductsByCategory(category),
                HttpStatus.OK
        );
    }

    // get all the products in a category who have price greater than 500
    @GetMapping("/get/category/{category}/price-greater-than/{price}")
    public ResponseEntity<List<ProductResponseDto>> getProductsByCategoryPriceGreaterThan(
            @PathVariable Category category,
            @PathVariable int price) {

        return new ResponseEntity<>(
                productService.getProductsByCategoryPriceGreaterThan(category, price),
                HttpStatus.OK
        );
    }

    // get the top 5 cheapest products in a category
    @GetMapping("/get/category/{category}/cheapest")
    public ResponseEntity<List<ProductResponseDto>> getTop5CheapestProducts(
            @PathVariable Category category) {

        return new ResponseEntity<>(
                productService.getTop5CheapestProducts(category),
                HttpStatus.OK
        );
    }

    // get top 5 costliest products in a category
    @GetMapping("/get/category/{category}/costliest")
    public ResponseEntity<List<ProductResponseDto>> getTop5CostliestProducts(
            @PathVariable Category category) {

        return new ResponseEntity<>(
                productService.getTop5CostliestProducts(category),
                HttpStatus.OK
        );
    }

    // get all the products of seller based on emailid
    @GetMapping("/get/seller")
    public ResponseEntity<List<ProductResponseDto>> getProductsBySellerEmail(
            @RequestParam String emailId) {

        return new ResponseEntity<>(
                productService.getProductsBySellerEmail(emailId),
                HttpStatus.OK
        );
    }

    // get all the out of stock products for a particular catgeory
    @GetMapping("/get/category/{category}/out-of-stock")
    public ResponseEntity<List<ProductResponseDto>> getOutOfStockProducts(
            @PathVariable Category category) {

        return new ResponseEntity<>(
                productService.getOutOfStockProductsByCategory(category),
                HttpStatus.OK
        );
    }

    // send an email to the seller of the product if the product is out os stock.


}
