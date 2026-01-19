package com.example.FlipCommerce.service;

import com.example.FlipCommerce.Enum.Category;
import com.example.FlipCommerce.dto.RequestDto.SellerRequestDto;
import com.example.FlipCommerce.dto.ResponseDto.ProductResponseDto;
import com.example.FlipCommerce.dto.ResponseDto.SellerResponseDto;
import com.example.FlipCommerce.model.Seller;
import com.example.FlipCommerce.repository.ProductRepository;
import com.example.FlipCommerce.repository.SellerRepository;
import com.example.FlipCommerce.transformer.ProductTransformer;
import com.example.FlipCommerce.transformer.SellerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerService {

    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    ProductRepository productRepository;

    public SellerResponseDto addSeller(SellerRequestDto sellerRequestDto){

        // dto -> entity
        Seller seller = SellerTransformer.SellerRequestDtoToSeller(sellerRequestDto);
        Seller savedSeller = sellerRepository.save(seller);
        // prepare response Dto
        return SellerTransformer.SellerToSellerResponseDto(savedSeller);
    }

    // 1. update seller info based on email
    public SellerResponseDto updateSellerByEmail(String emailId, SellerRequestDto sellerRequestDto) {

        Seller seller = sellerRepository.findByEmailId(emailId);

        seller.setName(sellerRequestDto.getName());
        seller.setMobNo(sellerRequestDto.getMobNo());

        Seller updatedSeller = sellerRepository.save(seller);
        return SellerTransformer.SellerToSellerResponseDto(updatedSeller);
    }

    public List<SellerResponseDto> getSellersByCategory(Category category) {

        List<Seller> sellers = sellerRepository.findAll();

        return sellers.stream()
                .filter(seller ->
                        seller.getProducts().stream()
                                .anyMatch(product -> product.getCategory() == category)
                )
                .map(SellerTransformer::SellerToSellerResponseDto)
                .toList();
    }

    // 3. get all products sold by a seller in a category
    public List<ProductResponseDto> getProductsBySellerAndCategory(String emailId, Category category) {

        Seller seller = sellerRepository.findByEmailId(emailId);

        return seller.getProducts().stream()
                .filter(product -> product.getCategory() == category)
                .map(ProductTransformer::ProducToProductResponseDto)
                .toList();
    }

    // 4. seller with highest number of products
    public SellerResponseDto sellerWithMaxProducts() {

        Seller seller = sellerRepository.findAll()
                .stream()
                .max((s1, s2) -> s1.getProducts().size() - s2.getProducts().size())
                .get();

        return SellerTransformer.SellerToSellerResponseDto(seller);
    }

    // 5. seller with minimum number of products
    public SellerResponseDto sellerWithMinProducts() {

        Seller seller = sellerRepository.findAll()
                .stream()
                .min((s1, s2) -> s1.getProducts().size() - s2.getProducts().size())
                .get();

        return SellerTransformer.SellerToSellerResponseDto(seller);
    }
}
