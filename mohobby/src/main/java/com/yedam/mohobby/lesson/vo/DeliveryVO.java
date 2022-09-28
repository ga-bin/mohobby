package com.yedam.mohobby.lesson.vo;

import lombok.Data;

@Data
public class DeliveryVO {
    private int payId;
    private String classId;
    private int prodId;
    private String deliveryNo;
    private String deliveryCorp;
    private int deliveryStatus;
}
