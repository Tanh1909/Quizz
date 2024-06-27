package com.example.quizz.service.serviceImpl;

import com.cloudinary.Cloudinary;
import com.example.quizz.exception.AppException;
import com.example.quizz.exception.ErrorCode;
import com.example.quizz.service.UploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
@Service
public class UploadFileServiceImpl implements UploadFileService {
    @Autowired
    private Cloudinary cloudinary;
    @Override
    public String upload(MultipartFile multipartFile) {
        try {
            Map<?,?> data=cloudinary.uploader().upload(multipartFile.getBytes(),Map.of());
            return (String) data.get("url");
        } catch (IOException e) {
            throw new AppException(ErrorCode.UPLOAD_FILE_FAIL);
        }
    }
}
