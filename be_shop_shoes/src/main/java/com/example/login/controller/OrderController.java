package com.example.login.controller;

import com.example.login.dto.OrderDTO;
import com.example.login.dto.OrderRequest;
import com.example.login.dto.RevenueDTO;
import com.example.login.model.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.login.model.service.OrderService;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<Order> createOrder(
            @RequestBody OrderRequest orderRequest) {

        try {
            Order newOrder = orderService.createOrder(
                    orderRequest.getPaymentMethod(),
                    orderRequest.getShippingAddress(),
                    orderRequest.getCusName(),
                    orderRequest.getCusPhone(),
                    orderRequest.getCusEmail(),
                    orderRequest.getOrderNote(),
                    orderRequest.getUserId(),
                    orderRequest.getToTal()
            );
            return new ResponseEntity<>(newOrder, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user/{userId}")
    public List<Order> getOrdersByUserId(@PathVariable Long userId) {
        return orderService.getOrdersByUserId(userId);
    }


    @PutMapping("/{orderId}/status")
    public ResponseEntity<Order> updateOrderStatus(@PathVariable Long orderId, @RequestParam String status) {
        Order order = orderService.updateOrderStatus(orderId, status);
        return ResponseEntity.ok(order);
    }

    @PutMapping("/{orderId}/statusPayment")
    public ResponseEntity<Order> updateOrderStatusPayment(@PathVariable Long orderId, @RequestParam String status_payment) {
        Order order = orderService.updateOrderStatusPayment(orderId, status_payment);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/all")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/revenue/date")
    public ResponseEntity<RevenueDTO> getRevenueByDate(@RequestParam String date) {
        // Lấy doanh thu theo ngày từ service
        RevenueDTO revenue = orderService.getRevenueByDate(date);
        return ResponseEntity.ok(revenue); // Trả về doanh thu dưới dạng JSON
    }

    @GetMapping("/revenue/month")
    public ResponseEntity<Object> getRevenueByMonth(@RequestParam int year, @RequestParam int month) {
        Object revenue = orderService.getRevenueByMonth(year, month);
        return ResponseEntity.ok(revenue);
    }

    @GetMapping("/revenue/year")
    public ResponseEntity<Object> getRevenueByYear(@RequestParam int year) {
        Object revenue = orderService.getRevenueByYear(year);
        return ResponseEntity.ok(revenue);
    }
}
