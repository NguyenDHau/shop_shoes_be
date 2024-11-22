//package com.example.login.model.service;
//
//import com.example.login.model.repository.OrderRepository;
//import com.example.login.model.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//
//public class DashboardService {
//
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private OrderRepository orderRepository;
//
//
//    public Long getTotalAccounts() {
//        return userRepository.countAccounts();
//    }
//
//    public Object getRevenueByDate(String date) {
//        return orderRepository.findRevenueByDate(date);
//    }
//
//    public Object getRevenueByMonth(int year, int month) {
//        return orderRepository.findRevenueByMonth(year, month);
//    }
//
//    public Object getRevenueByYear(int year) {
//        return orderRepository.findRevenueByYear(year);
//    }
//
//}
