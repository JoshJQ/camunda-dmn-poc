package com.db.camunda.ut.controller;

import static org.junit.jupiter.api.Assertions.*;

import com.db.camunda.controller.AdminController;
import com.db.camunda.entity.DmnTemplate;
import com.db.camunda.service.DmnTemplateService;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

@ExtendWith(MockitoExtension.class)
class AdminControllerTest {
    @Mock private DmnTemplateService dmnTemplateService;

    @InjectMocks private AdminController adminController;

    @Test
    void importDecisionRules() throws IOException {
        MultipartFile file = new MockMultipartFile("testFile", new byte[1]);
        Mockito.when(dmnTemplateService.convertDmnTemplate(Mockito.any()))
                .thenReturn(new DmnTemplate());
        assertEquals(HttpStatus.OK, adminController.importDecisionRules(file).getStatusCode());
    }
}
