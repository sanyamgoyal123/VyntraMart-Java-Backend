package com.shopping.service;


import java.util.Collection;

import com.shopping.entity.Cart;
import com.shopping.entity.ProductInOrder;
import com.shopping.entity.User;

public interface CartService {
    Cart getCart(User user);

    void mergeLocalCart(Collection<ProductInOrder> productInOrders, User user);

    void delete(String itemId, User user);

    void checkout(User user);
}
