package com.db.camunda.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.db.camunda.response.BasicResponseMessage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AdminControllerIntegrationTest {
    private static final String TEST_HOST = "http://localhost:";

    @LocalServerPort private int port;

    @Autowired private TestRestTemplate restTemplate;

    @Test
    public void importDecisionRules() {
        LinkedMultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("file", new FileSystemResource("testExcelFiles/testRules.xlsx"));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        ResponseEntity<BasicResponseMessage> responseEntity =
                this.restTemplate.postForEntity(
                        TEST_HOST + port + "/admin/rules/import",
                        new HttpEntity<>(map, headers),
                        BasicResponseMessage.class);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }
}
