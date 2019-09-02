package com.eigenbaumarkt.synctwodatabases.dao.product;

import com.eigenbaumarkt.synctwodatabases.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
