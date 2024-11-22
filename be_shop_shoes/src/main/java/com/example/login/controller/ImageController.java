package com.example.login.controller;

import com.example.login.model.service.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ImageController {

    @Autowired
    private CloudinaryService cloudinaryService;

    @DeleteMapping("/delete-image")
    public Map<String, Object> deleteImage(@RequestParam String publicId) {
        Map<String, Object> response = new HashMap<>();
        try {
            Map result = cloudinaryService.deleteImage(publicId);
            response.put("status", "success");
            response.put("result", result);
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", e.getMessage());
        }
        return response;
    }
}
