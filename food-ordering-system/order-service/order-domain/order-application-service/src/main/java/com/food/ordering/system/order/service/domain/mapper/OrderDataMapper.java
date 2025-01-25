package com.food.ordering.system.order.service.domain.mapper;

import com.food.ordering.system.domain.valueobject.CustomerId;
import com.food.ordering.system.domain.valueobject.Money;
import com.food.ordering.system.domain.valueobject.ProductId;
import com.food.ordering.system.domain.valueobject.RestaurantId;
import com.food.ordering.system.order.service.domain.dto.create.CreateOrderCommand;
import com.food.ordering.system.order.service.domain.dto.create.CreateOrderResponse;
import com.food.ordering.system.order.service.domain.dto.create.OrderAddress;
import com.food.ordering.system.order.service.domain.dto.create.OrderItemDto;
import com.food.ordering.system.order.service.domain.dto.track.TrackOrderResponse;
import com.food.ordering.system.order.service.domain.entity.Order;
import com.food.ordering.system.order.service.domain.entity.OrderItem;
import com.food.ordering.system.order.service.domain.entity.Product;
import com.food.ordering.system.order.service.domain.entity.Restaurant;
import com.food.ordering.system.order.service.domain.valueobject.StreetAddress;
import com.food.ordering.system.order.service.domain.valueobject.TrackingId;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class OrderDataMapper {
    public Restaurant createOrderCommandToRestaurant(CreateOrderCommand createOrderCommand) {
        return new Restaurant.Builder()
                .id(new RestaurantId(createOrderCommand.getRestaurantId()))
                .products(createOrderCommand.getOrderItemDtos().stream().map(
                        orderItem -> new Product(new ProductId(orderItem.getProductId()))).toList()
                ).build();
    }

    public Order createOrderCommandToOrder(CreateOrderCommand createOrderCommand) {
        return new Order.Builder(new CustomerId(createOrderCommand.getCustomerId()),
                new RestaurantId(createOrderCommand.getRestaurantId()),
                orderAddressToStreetAddress(createOrderCommand.getOrderAddress()),
                new Money(createOrderCommand.getPrice()),
                orderItemToOrderItemEntities(createOrderCommand.getOrderItemDtos())
        ).build();
    }

    public TrackOrderResponse orderToTrackOrderResponse(Order order) {
        return TrackOrderResponse.builder()
                .orderTrackingId(order.getTrackingId().getValue())
                .orderStatus(order.getOrderStatus())
                .failureMessages(order.getFailureMessages())
                .build();
    }

    public CreateOrderResponse orderToCreateOrderResponse(Order order) {
        return CreateOrderResponse.builder()
                .orderTrackingId(order.getTrackingId().getValue())
                .orderStatus(order.getOrderStatus())
                .build();
    }

    private StreetAddress orderAddressToStreetAddress(OrderAddress orderAddress) {
        return new StreetAddress(
                UUID.randomUUID(),
                orderAddress.getStreet(),
                orderAddress.getPostalCode(),
                orderAddress.getCity()
        );
    }

    private List<OrderItem> orderItemToOrderItemEntities(List<OrderItemDto> orderItems) {
        return orderItems.stream().map(this::orderItemToOrderItemEntity).toList();
    }

    private OrderItem orderItemToOrderItemEntity(OrderItemDto orderItemDto) {
        return new OrderItem.Builder(
                new Product(new ProductId(orderItemDto.getProductId())),
                orderItemDto.getQuantity(),
                new Money(orderItemDto.getPrice()),
                new Money(orderItemDto.getSubTotal())
        ).build();
    }
}
