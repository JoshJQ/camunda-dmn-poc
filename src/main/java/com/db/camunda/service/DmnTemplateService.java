package com.db.camunda.service;

import com.db.camunda.entity.DmnTemplate;
import java.io.IOException;
import java.io.InputStream;

public interface DmnTemplateService {
    DmnTemplate convertDmnTemplate(InputStream inputStream) throws IOException;
}
