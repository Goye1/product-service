package com.paygoal.productservice.service;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.paygoal.productservice.dto.ProductDTO;
import com.paygoal.productservice.exceptions.ProductAlreadyExistsException;
import com.paygoal.productservice.exceptions.ProductNotFoundException;
import com.paygoal.productservice.persistance.entity.Product;
import com.paygoal.productservice.persistance.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ProductService {
    private final IProductRepository productRepository;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    public ProductService(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public ProductDTO createProduct(Product product) throws ProductAlreadyExistsException {
        Optional<Product> existingProduct = productRepository.findByName(product.getName());
        if (existingProduct.isPresent()) {
            throw new ProductAlreadyExistsException("Product with name " + product.getName() + " already exists.");
        }
        Product savedProduct = productRepository.save(product);
        return objectMapper.convertValue(savedProduct, ProductDTO.class);
    }


    public ProductDTO updateProduct(Product product) throws ProductNotFoundException, JsonMappingException {
        Optional<Product> existingProductOptional = productRepository.findById(product.getId());
        if (existingProductOptional.isEmpty()) {
            throw new ProductNotFoundException("Product with ID " + product.getId() + " not found.");
        }
        Product existingProduct = existingProductOptional.get();
        objectMapper.updateValue(existingProduct, product);
        productRepository.save(existingProduct);
        return objectMapper.convertValue(existingProduct, ProductDTO.class);
    }


    public Object findProduct(Long id, String name) throws ProductNotFoundException {
        if (id != null) {
            Optional<Product> productOptional = productRepository.findById(id);
            if (productOptional.isPresent()) {
                return objectMapper.convertValue(productOptional.get(), ProductDTO.class);
            } else {
                throw new ProductNotFoundException("Product with id " + id + " not found.");
            }
        }
        if (name != null) {
            List<Product> products = productRepository.listByName(name);
            if (!products.isEmpty()) {
                return products.stream()
                        .map(product -> objectMapper.convertValue(product, ProductDTO.class))
                        .collect(Collectors.toList());
            } else {
                throw new ProductNotFoundException("Product with name " + name + " not found.");
            }
        }
        throw new IllegalArgumentException("Both id and name cannot be null.");
    }

    public List<ProductDTO> listAllProductsOrderedByPriceAsc() {
        List<Product> products = productRepository.findAllOrderedByPriceAsc();
        return products.stream()
                .map(product -> objectMapper.convertValue(product, ProductDTO.class))
                .collect(Collectors.toList());
    }

    public List<ProductDTO> findAllOrderedByPriceDesc() {
        List<Product> products = productRepository.findAllOrderedByPriceDesc();
        return products.stream()
                .map(product -> objectMapper.convertValue(product, ProductDTO.class))
                .collect(Collectors.toList());
    }


    public void deleteProduct(Long id) throws ProductNotFoundException {
        Optional<Product> existingProductOptional = productRepository.findById(id);
        if (existingProductOptional.isEmpty()) {
            throw new ProductNotFoundException("Product with ID " + id + " not found.");
        }
        productRepository.delete(existingProductOptional.get());
    }



}