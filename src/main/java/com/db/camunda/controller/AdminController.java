package com.db.camunda.controller;

import com.db.camunda.response.BasicResponseMessage;
import com.db.camunda.service.DmnTemplateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(value = "/admin")
@Slf4j
public class AdminController {

    private DmnTemplateService dmnTemplateService;

    public AdminController(DmnTemplateService dmnTemplateService) {
        this.dmnTemplateService = dmnTemplateService;
    }

    @PostMapping(value = "/rules/import")
    public ResponseEntity<BasicResponseMessage> importDecisionRules(@RequestParam("file")MultipartFile file) {
        try {
            log.info("Importing new excel files");
            dmnTemplateService.convertDmnTemplate(file.getInputStream());
            return ResponseEntity
                    .ok(new BasicResponseMessage("Rules are imported successfully"));
        } catch (IOException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new BasicResponseMessage("Failed to convert DMN template file"));
        }

    }
}
