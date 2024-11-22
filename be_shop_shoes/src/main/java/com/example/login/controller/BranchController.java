package com.example.login.controller;

import com.example.login.model.entity.Branch;
import com.example.login.model.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/branches")
@CrossOrigin(origins = "*")
public class BranchController {

    @Autowired
    private BranchService branchService;

    // Lấy danh sách tất cả các branch
    @GetMapping
    public ResponseEntity<List<Branch>> getAllBranches() {
        List<Branch> branches = branchService.getAllBranches();
        return new ResponseEntity<>(branches, HttpStatus.OK);
    }

    // Lấy branch theo ID
    @GetMapping("/{id}")
    public ResponseEntity<Branch> getBranchById(@PathVariable Long id) {
        Optional<Branch> branch = branchService.getBranchById(id);
        return branch.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Tạo mới branch
    @PostMapping
    public ResponseEntity<Branch> createBranch(@RequestBody Branch branch) {
        Branch savedBranch = branchService.saveOrUpdateBranch(branch);
        return new ResponseEntity<>(savedBranch, HttpStatus.CREATED);
    }

    // Cập nhật branch
    @PutMapping("/{id}")
    public ResponseEntity<Branch> updateBranch(@PathVariable Long id, @RequestBody Branch branch) {
        Optional<Branch> existingBranch = branchService.getBranchById(id);
        if (existingBranch.isPresent()) {
            branch.setId(id);  // Đảm bảo ID của branch cần cập nhật
            Branch updatedBranch = branchService.saveOrUpdateBranch(branch);
            return new ResponseEntity<>(updatedBranch, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Xóa branch
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBranch(@PathVariable Long id) {
        Optional<Branch> branch = branchService.getBranchById(id);
        if (branch.isPresent()) {
            branchService.deleteBranch(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Get Branch ID by branchName (new method)
    @GetMapping("/name/{branchName}")
    public ResponseEntity<Long> getBranchIdByName(@PathVariable String branchName) {
        Long branchId = branchService.getBranchIdByName(branchName);
        if (branchId != null) {
            return new ResponseEntity<>(branchId, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

