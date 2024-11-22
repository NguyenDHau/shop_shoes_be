package com.example.login.model.service;

import com.example.login.dto.OrderDetailDTO;
import com.example.login.model.entity.OrderDetail;
import com.example.login.model.repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderDetailService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    // Lưu OrderDetail
    public OrderDetail saveOrderDetail(OrderDetail orderDetail) {
        return orderDetailRepository.save(orderDetail);
    }

    // Lấy tất cả OrderDetail
    public List<OrderDetail> getAllOrderDetails() {
        return orderDetailRepository.findAll();
    }

    // Lấy OrderDetail theo ID
    public OrderDetail getOrderDetailById(Long id) {
        return orderDetailRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("OrderDetail not found with id " + id));
    }

    // Xóa OrderDetail theo ID
    public void deleteOrderDetail(Long id) {
        orderDetailRepository.deleteById(id);
    }


    public List<OrderDetailDTO> getOrderDetailsByOrderId(Long orderId) {
        List<Object[]> results = orderDetailRepository.findOrderDetailsByOrderId(orderId);
        List<OrderDetailDTO> orderDetails = new ArrayList<>();

        // Chuyển đổi từng hàng dữ liệu từ Object[] sang OrderDetailDTO
        for (Object[] row : results) {
            OrderDetailDTO dto = new OrderDetailDTO(
                    ((BigInteger) row[0]).longValue(), // orderId
                    (Integer) row[1], // quantity
                    (String) row[2], // productName
                    (String) row[3], // colorName
                    (String) row[4], // sizeName
                    (Double) row[5], // price
                    (String) row[6]  // fileUrl
            );
            orderDetails.add(dto);
        }
        return orderDetails;
    }
}

