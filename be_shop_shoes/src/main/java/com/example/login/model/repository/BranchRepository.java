package com.example.login.model.repository;

import com.example.login.model.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Long> {
    // Tạo phương thức tìm kiếm Branch bằng branchName nếu cần
    Branch findByBranchName(String branchName);

    // Tạo phương thức tìm kiếm id bằng branchUrl (nếu cần)
    Long findIdByBranchUrl(String branchUrl);

    // Trả về id của chi nhánh theo tên
    Long findIdByBranchName(String branchName);
}

