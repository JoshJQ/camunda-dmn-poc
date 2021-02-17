package com.db.camunda.service.impl;

import com.db.camunda.service.DmnService;
import java.nio.file.Paths;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.dmn.engine.DmnDecision;
import org.camunda.bpm.dmn.engine.DmnEngine;
import org.camunda.bpm.dmn.engine.DmnEngineConfiguration;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.model.dmn.Dmn;
import org.camunda.bpm.model.dmn.DmnModelInstance;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DmnServiceImpl implements DmnService {

    private DmnEngine dmnEngine;
    private DmnDecision decision;

    @Override
    @PostConstruct
    public void initDmnEngine() {
        dmnEngine = DmnEngineConfiguration.createDefaultDmnEngineConfiguration().buildEngine();

        DmnModelInstance dmnModelInstance =
                Dmn.readModelFromFile(Paths.get("rules/testTemplate.dmn").toFile());
        decision = dmnEngine.parseDecision("test_rules", dmnModelInstance);
    }

    @Override
    public Long decideQuarter(Integer month) {
        VariableMap variables = Variables.createVariables().putValue("month", month);
        Long quarter =
                dmnEngine
                        .evaluateDecision(decision, variables)
                        .getSingleResult()
                        .<Long>getEntry("quarter");
        log.info("quarter is {}", quarter);
        return quarter;
    }
}
