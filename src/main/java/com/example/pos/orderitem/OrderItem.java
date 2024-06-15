package com.example.pos.orderitem;

import com.example.pos.item.Item;
import com.example.pos.order.Order;
import jakarta.persistence.*;

@Entity
@Table
public class OrderItem {

    @Id
    @SequenceGenerator(
            name = "orderitem_sequence",
            sequenceName = "orderitem_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "orderitem_sequence"
    )

    private Long id;

    private Long itemId;

    private Long orderId;

    private int quantity;

    private double price;

    public OrderItem() {
    }

    public OrderItem(Long itemId, Long orderId, int quantity, double price) {
        this.itemId = itemId;
        this.orderId = orderId;
        this.quantity = quantity;
        this.price = price;
    }

    public OrderItem(Long itemId, int quantity, double price) {
        this.itemId = itemId;
        this.quantity = quantity;
        this.price = price;
    }

    public OrderItem(Long id, Long itemId, Long orderId, int quantity, double price) {
        this.id = id;
        this.itemId = itemId;
        this.orderId = orderId;
        this.quantity = quantity;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
