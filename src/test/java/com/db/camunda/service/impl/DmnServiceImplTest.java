package com.db.camunda.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import com.db.camunda.service.DmnService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DmnServiceImplTest {
    @Autowired private DmnService dmnService;

    @Test
    void decideQuarter() {
        assertEquals(1, dmnService.decideQuarter(2));
        assertEquals(2, dmnService.decideQuarter(5));
        assertEquals(3, dmnService.decideQuarter(8));
        assertEquals(4, dmnService.decideQuarter(11));
    }
}
