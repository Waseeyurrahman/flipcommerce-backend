package com.example.FlipCommerce.repository;

import com.example.FlipCommerce.Enum.Gender;
import com.example.FlipCommerce.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    Customer findByEmailId(String emailId);

    List<Customer> findByGenderAndAgeBetween(Gender gender, int minAge, int maxAge);

    List<Customer> findByGenderAndAgeLessThan(Gender gender, int age);


}
