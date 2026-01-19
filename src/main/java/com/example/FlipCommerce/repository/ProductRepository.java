package com.example.FlipCommerce.repository;

import com.example.FlipCommerce.Enum.Category;
import com.example.FlipCommerce.Enum.ProductStatus;
import com.example.FlipCommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findByCategoryAndPrice(Category category, int price);

    // get all products of a category
    List<Product> findByCategory(Category category);

    // price greater than given value in a category
    List<Product> findByCategoryAndPriceGreaterThan(Category category, int price);

    // top 5 cheapest products in a category
    List<Product> findTop5ByCategoryOrderByPriceAsc(Category category);

    // top 5 costliest products in a category
    List<Product> findTop5ByCategoryOrderByPriceDesc(Category category);

    // products by seller email
    List<Product> findBySellerEmailId(String emailId);

    // out of stock products in a category
    List<Product> findByCategoryAndProductStatus(Category category, ProductStatus productStatus);
}
