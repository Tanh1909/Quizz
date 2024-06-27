package com.example.quizz.service;

import org.springframework.web.multipart.MultipartFile;

public interface UploadFileService {
    String upload(MultipartFile multipartFile);
}
