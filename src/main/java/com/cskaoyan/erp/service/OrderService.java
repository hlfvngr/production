package com.cskaoyan.erp.service;

import com.cskaoyan.erp.bean.Order;
import com.cskaoyan.erp.utils.PageModel;

import java.util.List;

public interface OrderService {

    List<Order> findAllOrder();

    List<Order> findAllOrder(Order order, PageModel pageModel);

    boolean insertOrder(Order order);

    boolean updateOrder(Order order);

    boolean updateOrderNote(Order order);

    boolean deleteOrder(String[] ids);

    Order findOrderById(String searchValue);
}
