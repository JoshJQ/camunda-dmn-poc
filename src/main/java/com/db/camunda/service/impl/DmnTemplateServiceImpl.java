package com.db.camunda.service.impl;

import com.db.camunda.common.Constants;
import com.db.camunda.entity.DmnTemplate;
import com.db.camunda.repository.DmnTemplateRepository;
import com.db.camunda.service.DmnTemplateService;
import java.io.*;
import java.util.Optional;
import org.camunda.bpm.dmn.xlsx.XlsxConverter;
import org.camunda.bpm.model.dmn.Dmn;
import org.camunda.bpm.model.dmn.DmnModelInstance;
import org.springframework.stereotype.Service;

@Service
public class DmnTemplateServiceImpl implements DmnTemplateService {

    private DmnTemplateRepository dmnTemplateRepository;

    public DmnTemplateServiceImpl(DmnTemplateRepository dmnTemplateRepository) {
        this.dmnTemplateRepository = dmnTemplateRepository;
    }

    @Override
    public DmnTemplate convertDmnTemplate(InputStream inputStream) throws IOException {
        XlsxConverter converter = new XlsxConverter();
        DmnModelInstance dmnModelInstance = converter.convert(inputStream);
        DmnTemplate dmnTemplate;
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            Dmn.writeModelToStream(outputStream, dmnModelInstance);
            String template = new String(outputStream.toByteArray());

            Optional<DmnTemplate> dmnTemplateOptional =
                    dmnTemplateRepository.findByTypeAndStatus(
                            Constants.DMN_RULE_TYPE, Constants.DMN_RULE_STATUS_ACTIVE);
            if (!dmnTemplateOptional.isPresent()) {
                dmnTemplate =
                        DmnTemplate.builder()
                                .type(Constants.DMN_RULE_TYPE)
                                .status(Constants.DMN_RULE_STATUS_ACTIVE)
                                .build();
            } else dmnTemplate = dmnTemplateOptional.get();
            dmnTemplate.setTemplate(template);
            dmnTemplate = dmnTemplateRepository.save(dmnTemplate);
        }
        return dmnTemplate;
    }
}
