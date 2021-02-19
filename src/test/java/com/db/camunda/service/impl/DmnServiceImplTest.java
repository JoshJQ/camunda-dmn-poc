package com.db.camunda.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import com.db.camunda.common.Constants;
import com.db.camunda.entity.DmnTemplate;
import com.db.camunda.repository.DmnTemplateRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DmnServiceImplTest {
    @Mock private DmnTemplateRepository dmnTemplateRepository;

    @InjectMocks private DmnServiceImpl dmnService;

    @Test
    void decideQuarter() {
        Mockito.when(
                        dmnTemplateRepository.findByTypeAndStatus(
                                Constants.DMN_RULE_TYPE, Constants.DMN_RULE_STATUS_ACTIVE))
                .thenReturn(
                        Optional.of(
                                DmnTemplate.builder()
                                        .type(Constants.DMN_RULE_TYPE)
                                        .status(Constants.DMN_RULE_STATUS_ACTIVE)
                                        .template(
                                                "<?xml version=\"1.0\" encoding=\"UTF-8\"?><definitions xmlns=\"http://www.omg.org/spec/DMN/20151101/dmn.xsd\" id=\"definitions\" name=\"definitions\" namespace=\"http://camunda.org/schema/1.0/dmn\">\n"
                                                        + "  <decision id=\"test_rules\" name=\"test_rules\">\n"
                                                        + "    <decisionTable id=\"decisionTable\">\n"
                                                        + "      <input id=\"month\">\n"
                                                        + "        <inputExpression id=\"InputExpression1162230295\">\n"
                                                        + "          <text>month</text>\n"
                                                        + "        </inputExpression>\n"
                                                        + "      </input>\n"
                                                        + "      <output id=\"quarter\" name=\"quarter\"/>\n"
                                                        + "      <rule id=\"excelRow2\">\n"
                                                        + "        <inputEntry id=\"A2\">\n"
                                                        + "          <text>1</text>\n"
                                                        + "        </inputEntry>\n"
                                                        + "        <outputEntry id=\"B2\">\n"
                                                        + "          <text>1</text>\n"
                                                        + "        </outputEntry>\n"
                                                        + "      </rule>\n"
                                                        + "      <rule id=\"excelRow3\">\n"
                                                        + "        <inputEntry id=\"A3\">\n"
                                                        + "          <text>2</text>\n"
                                                        + "        </inputEntry>\n"
                                                        + "        <outputEntry id=\"B3\">\n"
                                                        + "          <text>1</text>\n"
                                                        + "        </outputEntry>\n"
                                                        + "      </rule>\n"
                                                        + "      <rule id=\"excelRow4\">\n"
                                                        + "        <inputEntry id=\"A4\">\n"
                                                        + "          <text>3</text>\n"
                                                        + "        </inputEntry>\n"
                                                        + "        <outputEntry id=\"B4\">\n"
                                                        + "          <text>1</text>\n"
                                                        + "        </outputEntry>\n"
                                                        + "      </rule>\n"
                                                        + "      <rule id=\"excelRow5\">\n"
                                                        + "        <inputEntry id=\"A5\">\n"
                                                        + "          <text>4</text>\n"
                                                        + "        </inputEntry>\n"
                                                        + "        <outputEntry id=\"B5\">\n"
                                                        + "          <text>2</text>\n"
                                                        + "        </outputEntry>\n"
                                                        + "      </rule>\n"
                                                        + "      <rule id=\"excelRow6\">\n"
                                                        + "        <inputEntry id=\"A6\">\n"
                                                        + "          <text>5</text>\n"
                                                        + "        </inputEntry>\n"
                                                        + "        <outputEntry id=\"B6\">\n"
                                                        + "          <text>2</text>\n"
                                                        + "        </outputEntry>\n"
                                                        + "      </rule>\n"
                                                        + "      <rule id=\"excelRow7\">\n"
                                                        + "        <inputEntry id=\"A7\">\n"
                                                        + "          <text>6</text>\n"
                                                        + "        </inputEntry>\n"
                                                        + "        <outputEntry id=\"B7\">\n"
                                                        + "          <text>2</text>\n"
                                                        + "        </outputEntry>\n"
                                                        + "      </rule>\n"
                                                        + "      <rule id=\"excelRow8\">\n"
                                                        + "        <inputEntry id=\"A8\">\n"
                                                        + "          <text>7</text>\n"
                                                        + "        </inputEntry>\n"
                                                        + "        <outputEntry id=\"B8\">\n"
                                                        + "          <text>3</text>\n"
                                                        + "        </outputEntry>\n"
                                                        + "      </rule>\n"
                                                        + "      <rule id=\"excelRow9\">\n"
                                                        + "        <inputEntry id=\"A9\">\n"
                                                        + "          <text>8</text>\n"
                                                        + "        </inputEntry>\n"
                                                        + "        <outputEntry id=\"B9\">\n"
                                                        + "          <text>3</text>\n"
                                                        + "        </outputEntry>\n"
                                                        + "      </rule>\n"
                                                        + "      <rule id=\"excelRow10\">\n"
                                                        + "        <inputEntry id=\"A10\">\n"
                                                        + "          <text>9</text>\n"
                                                        + "        </inputEntry>\n"
                                                        + "        <outputEntry id=\"B10\">\n"
                                                        + "          <text>3</text>\n"
                                                        + "        </outputEntry>\n"
                                                        + "      </rule>\n"
                                                        + "      <rule id=\"excelRow11\">\n"
                                                        + "        <inputEntry id=\"A11\">\n"
                                                        + "          <text>10</text>\n"
                                                        + "        </inputEntry>\n"
                                                        + "        <outputEntry id=\"B11\">\n"
                                                        + "          <text>4</text>\n"
                                                        + "        </outputEntry>\n"
                                                        + "      </rule>\n"
                                                        + "      <rule id=\"excelRow12\">\n"
                                                        + "        <inputEntry id=\"A12\">\n"
                                                        + "          <text>11</text>\n"
                                                        + "        </inputEntry>\n"
                                                        + "        <outputEntry id=\"B12\">\n"
                                                        + "          <text>4</text>\n"
                                                        + "        </outputEntry>\n"
                                                        + "      </rule>\n"
                                                        + "      <rule id=\"excelRow13\">\n"
                                                        + "        <inputEntry id=\"A13\">\n"
                                                        + "          <text>12</text>\n"
                                                        + "        </inputEntry>\n"
                                                        + "        <outputEntry id=\"B13\">\n"
                                                        + "          <text>4</text>\n"
                                                        + "        </outputEntry>\n"
                                                        + "      </rule>\n"
                                                        + "    </decisionTable>\n"
                                                        + "  </decision>\n"
                                                        + "</definitions>")
                                        .build()));
        assertEquals(1, dmnService.decideQuarter(2));
        assertEquals(2, dmnService.decideQuarter(5));
        assertEquals(3, dmnService.decideQuarter(8));
        assertEquals(4, dmnService.decideQuarter(11));
    }
}
