package com.example.login.controller;

import com.example.login.dto.OrderDetailDTO;
import com.example.login.model.entity.OrderDetail;
import com.example.login.model.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order-details")
@CrossOrigin(origins = "*")
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;


    @PostMapping("/create")
    public OrderDetail createOrderDetail(@RequestBody OrderDetail orderDetail) {
        return orderDetailService.saveOrderDetail(orderDetail);
    }


    @GetMapping
    public List<OrderDetail> getAllOrderDetails() {
        return orderDetailService.getAllOrderDetails();
    }


    @GetMapping("/{id}")
    public OrderDetail getOrderDetailById(@PathVariable Long id) {
        return orderDetailService.getOrderDetailById(id);
    }


    @DeleteMapping("/{id}")
    public void deleteOrderDetail(@PathVariable Long id) {
        orderDetailService.deleteOrderDetail(id);
    }

    @GetMapping("/details/{orderId}")
    public ResponseEntity<List<OrderDetailDTO>> getOrderDetails(@PathVariable Long orderId) {
        List<OrderDetailDTO> orderDetails = orderDetailService.getOrderDetailsByOrderId(orderId);
        return ResponseEntity.ok(orderDetails);
    }
}
