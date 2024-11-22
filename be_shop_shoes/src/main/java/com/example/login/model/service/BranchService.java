package com.example.login.model.service;

import com.example.login.model.entity.Branch;
import com.example.login.model.repository.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BranchService {

    @Autowired
    private BranchRepository branchRepository;

    // Lấy tất cả các branch
    public List<Branch> getAllBranches() {
        return branchRepository.findAll();
    }

    // Lấy branch theo ID
    public Optional<Branch> getBranchById(Long id) {
        return branchRepository.findById(id);
    }

    // Tạo mới hoặc cập nhật branch
    public Branch saveOrUpdateBranch(Branch branch) {
        return branchRepository.save(branch);
    }

    // Xóa branch theo ID
    public void deleteBranch(Long id) {
        branchRepository.deleteById(id);
    }

    public Long getBranchIdByName(String branchName) {
        return branchRepository.findIdByBranchName(branchName);
    }
}

