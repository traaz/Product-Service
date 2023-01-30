package com.eleitech.stockmanagement.productservicee.controller;


import com.eleitech.stockmanagement.productservicee.enums.Language;
import com.eleitech.stockmanagement.productservicee.exception.enums.FriendlyMessageCodes;
import com.eleitech.stockmanagement.productservicee.exception.enums.utils.FriendlyMessageUtils;
import com.eleitech.stockmanagement.productservicee.repository.entity.Product;
import com.eleitech.stockmanagement.productservicee.request.ProductCreateRequest;
import com.eleitech.stockmanagement.productservicee.request.ProductUpdatedRequest;
import com.eleitech.stockmanagement.productservicee.response.FriendlyMessage;
import com.eleitech.stockmanagement.productservicee.response.InternalApiResponse;
import com.eleitech.stockmanagement.productservicee.response.ProductResponse;
import com.eleitech.stockmanagement.productservicee.service.IProductRepositoryService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/api/1.0/product")
@RequiredArgsConstructor
public class ProductController {
    private final IProductRepositoryService productRepositoryService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/{language}/create")
    public InternalApiResponse<ProductResponse> createdProduct(@PathVariable("language")Language language,
                                                               @RequestBody ProductCreateRequest productCreateRequest){

        Product product = productRepositoryService.createProduct(language, productCreateRequest);
        ProductResponse productResponse = convertProductResponse(product);
        return InternalApiResponse.<ProductResponse>builder()
                .friendlyMessage(FriendlyMessage.builder()
                        .title(FriendlyMessageUtils.getFriendlyMessage(language, FriendlyMessageCodes.SUCCESS))
                        .description(FriendlyMessageUtils.getFriendlyMessage(language, FriendlyMessageCodes.PRODUCT_SUCCESFULLY_CREATED))
                        .build())
                .httpStatus(HttpStatus.CREATED)
                .hasError(false)
                .payload(productResponse)
                .build();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value="/{language}/get/{productId}")
    public InternalApiResponse<ProductResponse> getProduct(@PathVariable("language")Language language,
                                                           @PathVariable("productId")Long productId){
        Product product = productRepositoryService.getProduct(language, productId);
        ProductResponse productResponse = convertProductResponse(product);
        return InternalApiResponse.<ProductResponse>builder()
                .httpStatus(HttpStatus.OK)
                .hasError(false)
                .payload(productResponse)
                .build();
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{language}/update/{productId}")
    public InternalApiResponse<ProductResponse> updateProduct(@PathVariable("language")Language language,
                                                              @PathVariable("productId")Long productId,
                                                              @RequestBody ProductUpdatedRequest productUpdatedRequest){
        Product product =productRepositoryService.updateProduct(language, productId, productUpdatedRequest);
        ProductResponse productResponse = convertProductResponse(product);
        return InternalApiResponse.<ProductResponse>builder()
                .friendlyMessage(FriendlyMessage.builder()
                        .title(FriendlyMessageUtils.getFriendlyMessage(language, FriendlyMessageCodes.SUCCESS))
                        .description(FriendlyMessageUtils.getFriendlyMessage(language, FriendlyMessageCodes.SUCCESS))
                        .build())
                .httpStatus(HttpStatus.OK)
                .hasError(false)
                .payload(productResponse)
                .build();
    }

    @ApiOperation(value="This endpoint get all products")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value="/{language}/products")
    public List<Product> getProducts(@PathVariable("language")Language language){
        /*List<Product> products = productRepositoryService.getProducts(language);
        List<ProductResponse> productResponses = convertProductResponseList(products);
        return InternalApiResponse.<List<ProductResponse>>builder()
                .httpStatus(HttpStatus.OK)
                .hasError(false)
                .payload(productResponses)
                .build();*/
        return productRepositoryService.getProducts(language);
    }
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value="{language}/delete/{productId}")
    public InternalApiResponse<ProductResponse> deleteProduct(@PathVariable("language")Language language,
                                                              @PathVariable("productId")Long productId){
        Product product=productRepositoryService.deleteProduct(language,productId);
        ProductResponse productResponse=convertProductResponse(product);
        return InternalApiResponse.<ProductResponse>builder()
                .friendlyMessage(FriendlyMessage.builder()
                        .title(FriendlyMessageUtils.getFriendlyMessage(language, FriendlyMessageCodes.SUCCESS))
                        .description(FriendlyMessageUtils.getFriendlyMessage(language, FriendlyMessageCodes.SUCCESS))
                        .build())
                .httpStatus(HttpStatus.OK)
                .hasError(false)
                .payload(productResponse)
                .build();
    }
    private List<ProductResponse> convertProductResponseList(List<Product> productList){
        return productList.stream()
                .map(arg -> ProductResponse.builder() //arg list icindeki herbir product
                        .productId(arg.getProductId())
                        .productName(arg.getProductName())
                        .quantity(arg.getQuantity())
                        .price(arg.getPrice())
                        .productCreatedDate(arg.getProductCreatedDate().getTime())
                        .productUpdatedDate(arg.getProductUpdatedDate().getTime())
                        .build())
                .collect(Collectors.toList());
    }



    private static ProductResponse convertProductResponse(Product product) {
        return ProductResponse.builder()
                .productId(product.getProductId())
                .productName(product.getProductName())
                .quantity(product.getQuantity())
                .price(product.getPrice())
                .productCreatedDate(product.getProductCreatedDate().getTime())
                .productUpdatedDate(product.getProductUpdatedDate().getTime())
                .build();
    }

}
