package com.shopping.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shopping.entity.ProductInOrder;
import com.shopping.entity.User;
import com.shopping.repository.ProductInOrderRepository;
import com.shopping.service.ProductInOrderService;

import java.util.concurrent.atomic.AtomicReference;


@Service
public class ProductInOrderServiceImpl implements ProductInOrderService {

    @Autowired
    ProductInOrderRepository productInOrderRepository;

    @Override
    @Transactional
    public void update(String itemId, Integer quantity, User user) {
     /*   var op = user.getCart().getProducts().stream().filter(e -> itemId.equals(e.getProductId())).findFirst();
        op.ifPresent(productInOrder -> {
            productInOrder.setCount(quantity);
            productInOrderRepository.save(productInOrder);
        });*/

    }

    @Override
    public ProductInOrder findOne(String itemId, User user) {
      /*  var op = user.getCart().getProducts().stream().filter(e -> itemId.equals(e.getProductId())).findFirst();
        AtomicReference<ProductInOrder> res = new AtomicReference<>();
        op.ifPresent(res::set);
        return res.get();*/
    	return null;
    }
}
