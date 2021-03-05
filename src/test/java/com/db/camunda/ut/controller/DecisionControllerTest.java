package com.db.camunda.ut.controller;

import static org.junit.jupiter.api.Assertions.*;

import com.db.camunda.controller.DecisionController;
import com.db.camunda.service.DmnService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

@ExtendWith(MockitoExtension.class)
class DecisionControllerTest {
    @Mock private DmnService dmnService;

    @InjectMocks private DecisionController decisionController;

    @Test
    void decideQuarter() {
        int month = 1;
        Mockito.when(dmnService.decideQuarter(month)).thenReturn(1L);
        assertEquals(HttpStatus.OK, decisionController.decideQuarter(month).getStatusCode());
    }
}
