package com.example.login.model.service;

import com.example.login.model.entity.Size;
import com.example.login.model.repository.SizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SizeService {

    @Autowired
    private SizeRepository sizeRepository;

    // Lấy danh sách tất cả các kích thước
    public List<Size> getAllSizes() {
        return sizeRepository.findAll();
    }

    // Lấy một kích thước theo ID
    public Size getSizeById(Long id) {
        Optional<Size> size = sizeRepository.findById(id);
        if (size.isPresent()) {
            return size.get();
        } else {
            throw new RuntimeException("Size not found with id: " + id);
        }
    }

    // Tạo mới một kích thước
    public Size createSize(Size size) {
        return sizeRepository.save(size);
    }

    // Cập nhật một kích thước
    public Size updateSize(Long id, Size sizeDetails) {
        Size size = getSizeById(id); // Lấy kích thước hiện tại
        size.setSizeName(sizeDetails.getSizeName());
        size.setOrder(sizeDetails.getOrder());
        return sizeRepository.save(size);
    }

    // Xóa một kích thước
    public void deleteSize(Long id) {
        sizeRepository.deleteById(id);
    }
}
