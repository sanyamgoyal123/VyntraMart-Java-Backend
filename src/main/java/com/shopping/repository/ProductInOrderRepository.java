package com.shopping.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.shopping.entity.ProductInOrder;


public interface ProductInOrderRepository extends JpaRepository<ProductInOrder, Long> {

}
