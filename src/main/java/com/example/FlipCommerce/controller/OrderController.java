package com.example.FlipCommerce.controller;

import com.example.FlipCommerce.dto.RequestDto.OrderRequestDto;
import com.example.FlipCommerce.dto.ResponseDto.OrderResponseDto;
import com.example.FlipCommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/place")
    public ResponseEntity placeOrder(@RequestBody OrderRequestDto orderRequestDto){

        try{
            OrderResponseDto orderResponseDto = orderService.placeOrder(orderRequestDto);
            return new ResponseEntity(orderResponseDto,HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // get top 5 orders with highest order value
    @GetMapping("/top5/highest-value")
    public List<OrderResponseDto> getTop5OrdersByValue(){
        return orderService.getTop5OrdersByValue();
    }


    // all the orders of a particular customer
    @GetMapping("/customer/{customerId}")
    public List<OrderResponseDto> getOrdersByCustomer(@PathVariable Integer customerId){
        return orderService.getOrdersByCustomer(customerId);
    }

    // top 5 orders of a customer based on cost
    @GetMapping("/customer/{customerId}/top5/value")
    public List<OrderResponseDto> getTop5OrdersByCustomerCost(@PathVariable Integer customerId) {
        return orderService.getTop5OrdersByCustomerCost(customerId);
    }


}
