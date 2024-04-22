package com.paygoal.productservice.persistance.repository;

import com.paygoal.productservice.persistance.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface IProductRepository  extends JpaRepository<Product,Long> {
    Optional<Product> findByName(String name);
    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Product> listByName(String name);

    @Query("SELECT p FROM Product p ORDER BY p.price ASC")
    List<Product> findAllOrderedByPriceAsc();

    @Query("SELECT p FROM Product p ORDER BY p.price DESC")
    List<Product> findAllOrderedByPriceDesc();
}
