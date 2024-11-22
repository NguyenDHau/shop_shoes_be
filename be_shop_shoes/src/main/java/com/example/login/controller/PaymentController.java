package com.example.login.controller;

import com.example.login.dto.MoMoCallbackResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PaymentController {

    @PostMapping("/momo-callback")
    public ResponseEntity<String> momoCallback(@RequestBody MoMoCallbackResponse callbackResponse) {

        return ResponseEntity.ok("Thanh toán thành công");

    }

//    private void updateOrderStatus(String orderId, String status) {
//        // Cập nhật trạng thái đơn hàng trong cơ sở dữ liệu
//        // Ví dụ sử dụng JPA repository để cập nhật trạng thái đơn hàng
//        Order order = orderRepository.findById(orderId);
//        order.setStatus(status);
//        orderRepository.save(order);
//    }
}

