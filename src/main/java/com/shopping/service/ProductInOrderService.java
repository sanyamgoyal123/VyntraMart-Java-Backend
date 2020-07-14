package com.shopping.service;

import com.shopping.entity.ProductInOrder;
import com.shopping.entity.User;

public interface ProductInOrderService {
    void update(String itemId, Integer quantity, User user);
    ProductInOrder findOne(String itemId, User user);
}
