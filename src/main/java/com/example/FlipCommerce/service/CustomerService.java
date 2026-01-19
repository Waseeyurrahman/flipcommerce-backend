package com.example.FlipCommerce.service;

import com.example.FlipCommerce.Enum.Gender;
import com.example.FlipCommerce.dto.RequestDto.CustomerRequestDto;
import com.example.FlipCommerce.dto.ResponseDto.CustomerResponseDto;
import com.example.FlipCommerce.model.Cart;
import com.example.FlipCommerce.model.Customer;
import com.example.FlipCommerce.repository.CustomerRepository;
import com.example.FlipCommerce.transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto){

        // dto -> entity
        Customer customer = CustomerTransformer.CustomerRequestDtoToCustomer(customerRequestDto);
        Cart cart = Cart.builder()
                .cartTotal(0)
                .customer(customer)
                .build();

        customer.setCart(cart);

        Customer savedCustomer = customerRepository.save(customer);  // saves both customer and cart
        // prepare response Dto
        return CustomerTransformer.CustomerToCustomerResponseDto(savedCustomer);

    }

    public List<CustomerResponseDto> getFemaleCustomerBetweenAge(int minAge, int maxAge) {
        return customerRepository
                .findByGenderAndAgeBetween(Gender.FEMALE,minAge,maxAge)
                .stream()
                .map(CustomerTransformer::CustomerToCustomerResponseDto)
                .collect(Collectors.toList());
    }

    public List<CustomerResponseDto> getMaleCustomersBelowAge(int age) {
       List<Customer> customers = customerRepository.findByGenderAndAgeLessThan(Gender.MALE,age);
               return customers .stream()
                .map(CustomerTransformer::CustomerToCustomerResponseDto)
                .collect(Collectors.toList());
    }


}
