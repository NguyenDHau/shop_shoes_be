package com.example.login.model.service;

import com.example.login.dto.RevenueDTO;
import com.example.login.model.entity.Inventory;
import com.example.login.model.entity.Order;
import com.example.login.model.entity.OrderDetail;
import com.example.login.model.repository.CartRepository;
import com.example.login.model.repository.InventoryRepository;
import com.example.login.model.repository.OrderDetailRepository;
import com.example.login.model.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;


    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found with id: " + orderId));
    }


    @Transactional
    public Order createOrder(String paymentMethod, String shippingAddress, String cusName, String cusPhone, String cusEmail, String orderNote, Long userId, Double toTal) {

        // Tạo đơn hàng
        Order order = new Order();
        order.setPaymentMethod(paymentMethod);
        order.setShippingAddress(shippingAddress);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("Loading");
        order.setCusName(cusName);
        order.setCusPhone(cusPhone);
        order.setCusEmail(cusEmail);
        order.setOrderNote(orderNote);
        order.setUserId(userId);
        order.setToTal(toTal);
        order.setStatus_payment("Loading");

        // Lưu Order trước
        Order savedOrder = orderRepository.save(order);



        return savedOrder;
    }

    public Order updateOrderStatus(Long orderId, String status) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
        order.setStatus(status);

        if ("Done".equals(status)) {
            // Truy vấn các chi tiết đơn hàng và cập nhật inventory
            List<OrderDetail> orderDetails = orderDetailRepository.findByOrderId(orderId);

            // Lặp qua các chi tiết đơn hàng để cập nhật inventory
            for (OrderDetail orderDetail : orderDetails) {
                Inventory inventory = inventoryRepository.findById(orderDetail.getInventoryId())
                        .orElseThrow(() -> new RuntimeException("Inventory item not found"));

                // Giảm số lượng trong kho theo số lượng của sản phẩm trong đơn hàng
                inventory.setQuantity(inventory.getQuantity() - orderDetail.getQuantity());

                // Lưu lại thay đổi trong inventory
                inventoryRepository.save(inventory);
            }
        }

        // Lưu lại trạng thái cập nhật của order
        return orderRepository.save(order);
    }

    public Order updateOrderStatusPayment(Long orderId, String status_payment) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
        order.setStatus_payment(status_payment);
        return orderRepository.save(order);
    }

    public List<Order> getOrdersByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public RevenueDTO getRevenueByDate(String dateString) {
        try {
            // Chuyển đổi chuỗi ngày thành java.util.Date
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(dateString);

            // Gọi phương thức repository để lấy doanh thu
            Object[] result = orderRepository.findRevenueByDate(date);

            // Kiểm tra và ép kiểu các phần tử trong mảng result
            Double sale = 0.0;
            Long countOrder = 0L;

            if (result != null && result.length == 2) {
                // Ép kiểu phần tử 0 (sale) thành Double
                if (result[0] instanceof Number) {
                    sale = ((Number) result[0]).doubleValue();
                }
                // Ép kiểu phần tử 1 (countOrder) thành Long
                if (result[1] instanceof Number) {
                    countOrder = ((Number) result[1]).longValue();  // Ép kiểu thành Long
                }
            }

            return new RevenueDTO(sale, countOrder);
        } catch (Exception e) {
            e.printStackTrace();
            // Xử lý lỗi chuyển đổi hoặc lỗi dữ liệu
            return new RevenueDTO(0.0, 0L);  // Trả về giá trị mặc định nếu có lỗi
        }
    }

    public Object getRevenueByMonth(int year, int month) {
        return orderRepository.findRevenueByMonth(year, month);
    }

    public Object getRevenueByYear(int year) {
        return orderRepository.findRevenueByYear(year);
    }
}