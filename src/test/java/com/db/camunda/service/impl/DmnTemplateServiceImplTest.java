package com.db.camunda.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import com.db.camunda.service.DmnTemplateService;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DmnTemplateServiceImplTest {
    @Autowired private DmnTemplateService dmnTemplateService;

    @Test
    void convertDmnTemplate() throws Exception {
        InputStream inputStream = new FileInputStream("testExcelFiles/testRules.xlsx");
        dmnTemplateService.convertDmnTemplate(inputStream);
        assertTrue(Files.exists(Paths.get("rules/testTemplate.dmn")));
    }
}
