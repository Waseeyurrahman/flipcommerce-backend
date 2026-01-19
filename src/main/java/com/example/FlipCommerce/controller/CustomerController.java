package com.example.FlipCommerce.controller;

import com.example.FlipCommerce.dto.RequestDto.CustomerRequestDto;
import com.example.FlipCommerce.dto.ResponseDto.CustomerResponseDto;
import com.example.FlipCommerce.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping("/add")
    public ResponseEntity<CustomerResponseDto> addCustomer(@RequestBody CustomerRequestDto customerRequestDto){

        CustomerResponseDto customerResponseDto = customerService.addCustomer(customerRequestDto);
        return new ResponseEntity(customerResponseDto, HttpStatus.CREATED);
    }

    // get all female customers between age 20-30
    @GetMapping("female/age")
    public ResponseEntity<List<CustomerResponseDto>> getFemaleCustomerBetweenAge(@RequestParam int minAge,
                                                                                 @RequestParam int maxAge){
        return ResponseEntity.ok(customerService.getFemaleCustomerBetweenAge(minAge, maxAge));
    }

    // get all male customers less than 45
    @GetMapping("male/age-less-than")
    public ResponseEntity<List<CustomerResponseDto>> getMaleCustomersBelowAge(@RequestParam int age){
        return ResponseEntity.ok(customerService.getMaleCustomersBelowAge(age));
    }



}
