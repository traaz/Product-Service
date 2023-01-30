package com.eleitech.stockmanagement.productservicee.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class ProductResponse {

    private long productId;
    private String productName;
    private int quantity;
    private double price;
    private Long productUpdatedDate;
    private Long productCreatedDate;
}
