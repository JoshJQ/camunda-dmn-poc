package com.db.camunda.service.impl;

import com.db.camunda.service.DmnTemplateService;
import org.camunda.bpm.dmn.xlsx.XlsxConverter;
import org.camunda.bpm.model.dmn.Dmn;
import org.camunda.bpm.model.dmn.DmnModelInstance;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Service
public class DmnTemplateServiceImpl implements DmnTemplateService {

    private final static String DMN_RULES_FOLDER = "rules";

    @PostConstruct
    public void init() throws IOException {
        Path path = Paths.get(DMN_RULES_FOLDER);
        if (!Files.exists(path))
            Files.createDirectory(path);
    }

    @Override
    public void convertDmnTemplate(InputStream inputStream) throws IOException {
        XlsxConverter converter = new XlsxConverter();
        DmnModelInstance dmnModelInstance = converter.convert(inputStream);
        try (OutputStream outputStream = new FileOutputStream(DMN_RULES_FOLDER + "/testTemplate.dmn")) {
            Dmn.writeModelToStream(outputStream, dmnModelInstance);
        }
    }
}
