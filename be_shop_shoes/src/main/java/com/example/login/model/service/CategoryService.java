package com.example.login.model.service;


import com.example.login.dto.CategoryResponseDto;
import com.example.login.model.entity.Category;
import com.example.login.model.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    // Tạo mới một danh mục
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    // Cập nhật một danh mục
    public Category updateCategory(Long id, Category category) {
        Optional<Category> existingCategory = categoryRepository.findById(id);
        if (existingCategory.isPresent()) {
            Category updatedCategory = existingCategory.get();
            updatedCategory.setName(category.getName());
            // Cập nhật các thuộc tính khác nếu cần
            return categoryRepository.save(updatedCategory);
        } else {
            throw new RuntimeException("Category not found");
        }
    }

    // Xóa một danh mục
    public void deleteCategory(Long id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
        } else {
            throw new RuntimeException("Category not found");
        }
    }

    // Lấy danh sách tất cả danh mục
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Long getIdByCategoryName(String name) {
        Optional<Category> category = categoryRepository.findByName(name);
        if (category.isPresent()) {
            return category.get().getId();
        } else {
            throw new RuntimeException("Category not found with name: " + name);
        }
    }
}


