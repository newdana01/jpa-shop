package jpabook.jpashop.repository.order.query;

import lombok.Data;

@Data
public class OrderItemQueryDto {
    private Long orderId;
    private String itemName;
    private int count;
    private int orderPrice;

    public OrderItemQueryDto(Long orderId, String itemName, int count, int orderPrice) {
        this.orderId = orderId;
        this.itemName = itemName;
        this.count = count;
        this.orderPrice = orderPrice;
    }
}
