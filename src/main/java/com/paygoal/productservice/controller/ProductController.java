package com.paygoal.productservice.controller;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.paygoal.productservice.dto.ProductDTO;
import com.paygoal.productservice.exceptions.ProductAlreadyExistsException;
import com.paygoal.productservice.exceptions.ProductNotFoundException;
import com.paygoal.productservice.persistance.entity.Product;
import com.paygoal.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @PostMapping("/create")
    public ResponseEntity<ProductDTO> createProduct(@RequestBody Product product) throws ProductAlreadyExistsException {
        ProductDTO productDTO = productService.createProduct(product);
        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<ProductDTO> updateProduct(@RequestBody Product product) throws JsonMappingException, ProductNotFoundException {
        ProductDTO productDTO = productService.updateProduct(product);
        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }
    @GetMapping("/order/asc")
    public ResponseEntity<List<ProductDTO>> listAllProductsOrderedByPriceAsc() {
        List<ProductDTO> products = productService.listAllProductsOrderedByPriceAsc();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/order/desc")
    public ResponseEntity<List<ProductDTO>> findAllOrderedByPriceDesc() {
        List<ProductDTO> products = productService.findAllOrderedByPriceDesc();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
    @GetMapping("/search")
    public ResponseEntity<?> findProduct(@RequestParam(required = false) Long id, @RequestParam(required = false) String name) throws ProductNotFoundException {
            Object product = productService.findProduct(id, name);
            return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) throws ProductNotFoundException {
        productService.deleteProduct(id);
        return new ResponseEntity<>("Product with id: " + id + " deleted", HttpStatus.OK);
    }



}
