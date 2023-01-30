package com.eleitech.stockmanagement.productservicee.service.impl;

import com.eleitech.stockmanagement.productservicee.enums.Language;
import com.eleitech.stockmanagement.productservicee.exception.enums.FriendlyMessageCodes;
import com.eleitech.stockmanagement.productservicee.exception.enums.exceptions.ProductNotCreteadException;
import com.eleitech.stockmanagement.productservicee.repository.entity.Product;
import com.eleitech.stockmanagement.productservicee.repository.entity.ProductRepository;
import com.eleitech.stockmanagement.productservicee.request.ProductCreateRequest;
import com.eleitech.stockmanagement.productservicee.request.ProductUpdatedRequest;
import com.eleitech.stockmanagement.productservicee.service.IProductRepositoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProductRepositoryServiceImpl implements IProductRepositoryService {
    private final ProductRepository productRepository;



    @Override
    public Product createProduct(Language language, ProductCreateRequest productCreateRequest) {
        try{
            Product product = Product.builder()
                    .productName(productCreateRequest.getProductName())
                    .quantity(productCreateRequest.getQuantity())
                    .price(productCreateRequest.getPrice())
                    .deleted(false)
                    .build();
            Product productResponse = productRepository.save(product);
            return productResponse;
        }catch (Exception exception){
            throw new ProductNotCreteadException(language, FriendlyMessageCodes.PRODUCT_NOT_CREATED_EXCEPTION, "product request: " + productCreateRequest.toString());
        }

    }

    @Override
    public Product getProduct(Language language, Long productId) {

        Product product = productRepository.getByProductIdAndDeletedFalse(productId);
        if(Objects.isNull(product)){
            throw new ProductNotCreteadException(language, FriendlyMessageCodes.ERROR, "product not found");

        }

        return product;
    }

    @Override
    public List<Product> getProducts(Language language) {
      //  List<Product> products = productRepository.getAllByDeletedFalse();
        return productRepository.findAll(Sort.by(Sort.Direction.ASC, "productId"));
    }

    @Override
    public Product updateProduct(Language language, Long productId, ProductUpdatedRequest productUpdatedRequest) {
        Product product = getProduct(language, productId);
        product.setProductName(productUpdatedRequest.getProductName());
        product.setQuantity(productUpdatedRequest.getQuantity());
        product.setPrice(productUpdatedRequest.getPrice());
        product.setProductCreatedDate(productUpdatedRequest.getProductCreatedDate());
        product.setProductUpdatedDate(new Date());
        Product productResponse = productRepository.save(product);
        return productResponse;
    }

    @Override
    public Product deleteProduct(Language language, Long productId) {

       Product product = getProduct(language, productId);
       product.setDeleted(true);
       product.setProductUpdatedDate(new Date());
       Product productResponse = productRepository.save(product);
       return productResponse;

    }
}
