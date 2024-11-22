//package com.example.login.controller;
//
//import com.example.login.model.service.DashboardService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/dashboard")
//@CrossOrigin(origins = "*")
//public class DashboardController {
//
//    @Autowired
//    private DashboardService dashboardService;
//
//    @GetMapping("/countUser")
//    public ResponseEntity<Long> countAccounts() {
//        Long count = dashboardService.getTotalAccounts();
//        return ResponseEntity.ok(count);
//    }
//
//    @GetMapping("/revenue/date")
//    public ResponseEntity<Object> getRevenueByDate(@RequestParam String date) {
//        Object revenue = dashboardService.getRevenueByDate(date);
//        return ResponseEntity.ok(revenue);
//    }
//
//    @GetMapping("/revenue/month")
//    public ResponseEntity<Object> getRevenueByMonth(@RequestParam int year, @RequestParam int month) {
//        Object revenue = dashboardService.getRevenueByMonth(year, month);
//        return ResponseEntity.ok(revenue);
//    }
//
//    @GetMapping("/revenue/year")
//    public ResponseEntity<Object> getRevenueByYear(@RequestParam int year) {
//        Object revenue = dashboardService.getRevenueByYear(year);
//        return ResponseEntity.ok(revenue);
//    }
//
//}
