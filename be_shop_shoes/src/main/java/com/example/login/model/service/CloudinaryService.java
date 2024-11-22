package com.example.login.model.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CloudinaryService {

    @Autowired
    private Cloudinary cloudinary;

    public Map deleteImage(String publicId) throws Exception {
        return cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
    }
}
