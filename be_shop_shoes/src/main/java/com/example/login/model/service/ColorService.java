package com.example.login.model.service;

import com.example.login.model.entity.Color;
import com.example.login.model.repository.ColorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ColorService {

    @Autowired
    private ColorRepository colorRepository;

    // Tạo mới một màu
    public Color createColor(Color color) {
        return colorRepository.save(color);
    }

    // Lấy tất cả các màu
    public List<Color> getAllColors() {
        return colorRepository.findAll();
    }

    // Lấy một màu theo ID
    public Color getColorById(Long id) {
        Optional<Color> color = colorRepository.findById(id);
        if (color.isPresent()) {
            return color.get();
        } else {
            throw new RuntimeException("Color not found with id: " + id);
        }
    }

    // Cập nhật một màu
    public Color updateColor(Long id, Color colorDetails) {
        Optional<Color> existingColor = colorRepository.findById(id);
        if (existingColor.isPresent()) {
            Color color = existingColor.get();
            color.setColorName(color.getColorName()); // Giả sử bạn có thuộc tính 'name'
            // Cập nhật các thuộc tính khác nếu cần
            return colorRepository.save(color);
        } else {
            throw new RuntimeException("Color not found with id: " + id);
        }
    }

    // Xóa một màu
    public void deleteColor(Long id) {
        if (colorRepository.existsById(id)) {
            colorRepository.deleteById(id);
        } else {
            throw new RuntimeException("Color not found with id: " + id);
        }
    }


}
