package com.db.camunda.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.db.camunda.response.BasicResponseMessage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DecisionControllerIntegrationTest {
    private static final String TEST_HOST = "http://localhost:";

    @LocalServerPort private int port;

    @Autowired private TestRestTemplate restTemplate;

    private void loadRules() {
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

    @Test
    public void decideQuarter() {
        this.loadRules();

        ResponseEntity<BasicResponseMessage> responseEntity =
                this.restTemplate.getForEntity(
                        TEST_HOST + port + "/decision/quarter/5", BasicResponseMessage.class);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }
}
