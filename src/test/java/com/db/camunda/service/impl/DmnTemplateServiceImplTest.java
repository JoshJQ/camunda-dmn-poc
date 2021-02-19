package com.db.camunda.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import com.db.camunda.common.Constants;
import com.db.camunda.entity.DmnTemplate;
import com.db.camunda.repository.DmnTemplateRepository;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DmnTemplateServiceImplTest {
    @Mock private DmnTemplateRepository dmnTemplateRepository;

    @InjectMocks private DmnTemplateServiceImpl dmnTemplateService;

    @Test
    void convertDmnTemplate() throws Exception {
        Mockito.when(
                        dmnTemplateRepository.findByTypeAndStatus(
                                Constants.DMN_RULE_TYPE, Constants.DMN_RULE_STATUS_ACTIVE))
                .thenReturn(Optional.empty());
        DmnTemplate dmnTemplate = new DmnTemplate();
        Mockito.when(dmnTemplateRepository.save(Mockito.any())).thenReturn(dmnTemplate);
        InputStream inputStream = new FileInputStream("testExcelFiles/testRules.xlsx");
        DmnTemplate result = dmnTemplateService.convertDmnTemplate(inputStream);
        assertEquals(dmnTemplate, result);
    }
}
