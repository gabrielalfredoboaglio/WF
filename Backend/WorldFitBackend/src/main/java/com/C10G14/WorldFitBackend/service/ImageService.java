package com.C10G14.WorldFitBackend.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    boolean isImageValid(MultipartFile image);
    String upload(MultipartFile image, String userEmail);
}
