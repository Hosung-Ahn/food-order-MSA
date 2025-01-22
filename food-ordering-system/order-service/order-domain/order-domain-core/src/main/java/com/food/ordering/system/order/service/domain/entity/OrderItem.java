package com.food.ordering.system.order.service.domain.entity;

import com.food.ordering.system.domain.entity.BaseEntity;
import com.food.ordering.system.domain.valueobject.Money;
import com.food.ordering.system.domain.valueobject.OrderId;
import com.food.ordering.system.order.service.domain.valueobject.OrderItemId;

public class OrderItem extends BaseEntity<OrderItemId> {
    private OrderId orderId;
    private final Product product;
    private final int quantity;
    private final Money price;
    private final Money subTotal;

    public OrderId getOrderId() {
        return orderId;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public Money getSubTotal() {
        return subTotal;
    }

    boolean isPriceValid() {
        return price.isGreaterThanZero() && price.equals(product.getPrice()) && price.multiply(quantity).equals(subTotal);
    }

    private OrderItem(Builder builder) {
        super.setId(builder.orderItemId);
        orderId = builder.orderId;
        product = builder.product;
        quantity = builder.quantity;
        price = builder.price;
        subTotal = builder.subTotal;
    }

    void initiateOrderItem(OrderId orderId, OrderItemId orderItemId) {
        this.orderId = orderId;
        setId(orderItemId);
    }


    public static final class Builder {
        private OrderItemId orderItemId;
        private OrderId orderId;
        private final Product product;
        private final int quantity;
        private final Money price;
        private final Money subTotal;

        public Builder(Product product, int quantity, Money price, Money subTotal) {
            this.product = product;
            this.quantity = quantity;
            this.price = price;
            this.subTotal = subTotal;
        }

        public Builder orderItemId(OrderItemId val) {
            orderItemId = val;
            return this;
        }

        public Builder orderId(OrderId val) {
            orderId = val;
            return this;
        }

        public OrderItem build() {
            return new OrderItem(this);
        }
    }
}
