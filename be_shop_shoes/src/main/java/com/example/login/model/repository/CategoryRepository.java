package com.example.login.model.repository;

import com.example.login.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    // Không cần thêm phương thức tùy chỉnh ở đây nếu không cần
    Optional<Category> findByName(String name);
}
