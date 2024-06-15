package com.example.pos.order;

import com.example.pos.item.Item;
import com.example.pos.item.ItemRepository;
import com.example.pos.orderitem.OrderItem;
import com.example.pos.orderitem.OrderItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    private final ItemRepository itemRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, OrderItemRepository orderItemRepository, ItemRepository itemRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.itemRepository = itemRepository;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public Order saveOrder(List<OrderItem> orders) {
        LocalDate localDate = LocalDate.now();
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());



        double total = 0;
        for (OrderItem orderItem : orders) {
            total += orderItem.getPrice() * orderItem.getQuantity();
        }
        Order newOrder = new Order(
                date,
                total
        );
        Order savedOrder =  orderRepository.save(newOrder);

        for (OrderItem orderItem : orders) {
            orderItem.setOrderId(savedOrder.getId());

            orderItemRepository.save(orderItem);
            System.out.println("Update item " + orderItem.getItemId());
            updateItem(orderItem.getItemId(), (orderItem.getQuantity()*-1));

        }

        return savedOrder;
    }

    @Transactional
    public void updateItem(Long itemId, int quantity) {
        try {
            Item item = itemRepository.findById(itemId).orElseThrow(() -> new IllegalStateException("Item not found"));
            int quantityLeft = item.getQuantity() + quantity;
            item.setQuantity(quantityLeft);
            itemRepository.save(item); // Ensure the item is saved after update
            System.out.println("Update item " + item.getName() + " to " + item.getQuantity());
        } catch (Exception e) {
            throw new IllegalStateException("Failed to update item " + e.toString());
        }

    }
}