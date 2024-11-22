package com.example.login.controller;

import com.example.login.model.entity.Size;
import com.example.login.model.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sizes")
@CrossOrigin(origins = "*")
public class SizeController {

    @Autowired
    private SizeService sizeService;

    // Lấy danh sách tất cả các kích thước
    @GetMapping
    public List<Size> getAllSizes() {
        return sizeService.getAllSizes();
    }

    // Lấy một kích thước theo ID
    @GetMapping("/{id}")
    public Size getSizeById(@PathVariable Long id) {
        return sizeService.getSizeById(id);
    }

    // Tạo mới một kích thước
    @PostMapping
    public Size createSize(@RequestBody Size size) {
        return sizeService.createSize(size);
    }

    // Cập nhật một kích thước
    @PutMapping("/{id}")
    public Size updateSize(@PathVariable Long id, @RequestBody Size sizeDetails) {
        return sizeService.updateSize(id, sizeDetails);
    }

    // Xóa một kích thước
    @DeleteMapping("/{id}")
    public void deleteSize(@PathVariable Long id) {
        sizeService.deleteSize(id);
    }
}
