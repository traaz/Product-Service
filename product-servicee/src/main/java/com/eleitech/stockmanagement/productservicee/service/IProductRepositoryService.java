package com.eleitech.stockmanagement.productservicee.service;

import com.eleitech.stockmanagement.productservicee.enums.Language;
import com.eleitech.stockmanagement.productservicee.repository.entity.Product;
import com.eleitech.stockmanagement.productservicee.request.ProductCreateRequest;
import com.eleitech.stockmanagement.productservicee.request.ProductUpdatedRequest;

import java.util.List;

public interface IProductRepositoryService {
    Product createProduct(Language language, ProductCreateRequest productCreateRequest);
    Product getProduct(Language language, Long productId);
    List<Product> getProducts(Language language);
    Product updateProduct(Language language, Long productId, ProductUpdatedRequest productUpdatedRequest);
    Product deleteProduct(Language language, Long productId);
}
