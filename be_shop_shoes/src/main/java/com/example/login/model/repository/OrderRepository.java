package com.example.login.model.repository;

import com.example.login.dto.OrderDTO;
import com.example.login.dto.RevenueDTO;
import com.example.login.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserId(Long userId); // Tìm kiếm đơn hàng theo userId
    // Doanh thu theo ngày cụ thể
    @Query(value = "SELECT SUM(o.to_tal) AS sale, COUNT(o.id) AS countOrder " +
            "FROM `order` o WHERE DATE(o.order_date) = :date", nativeQuery = true)
    Object[] findRevenueByDate(@Param("date") Date date);
    // Doanh thu theo tháng
    @Query("SELECT new com.example.login.dto.RevenueDTO(SUM(o.toTal), COUNT(o.id)) " +
            "FROM Order o " +
            "WHERE YEAR(o.orderDate) = :year AND MONTH(o.orderDate) = :month")
    RevenueDTO findRevenueByMonth(@Param("year") int year, @Param("month") int month);

    // Doanh thu theo năm
    @Query("SELECT new com.example.login.dto.RevenueDTO(SUM(o.toTal), COUNT(o.id)) " +
            "FROM Order o " +
            "WHERE YEAR(o.orderDate) = :year")
    RevenueDTO findRevenueByYear(@Param("year") int year);
}