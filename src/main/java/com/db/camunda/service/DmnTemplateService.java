package com.db.camunda.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public interface DmnTemplateService {
    void convertDmnTemplate(InputStream inputStream) throws IOException;
}
