package com.example.FlipCommerce.repository;

import com.example.FlipCommerce.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card,Integer> {

    Card findByCardNo(String cardNo);



}
