package com.eleitech.stockmanagement.productservicee.request;

import lombok.Data;

import java.util.Date;

@Data
public class ProductUpdatedRequest {
    private Long productId;
    private String productName;
    private Integer quantity;
    private Double price;

    private Date productCreatedDate;
    private Date productUpdatedDate;
}
