package com.db.camunda.service.impl;

import com.db.camunda.common.Constants;
import com.db.camunda.entity.DmnTemplate;
import com.db.camunda.repository.DmnTemplateRepository;
import com.db.camunda.service.DmnService;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.camunda.bpm.dmn.engine.DmnDecision;
import org.camunda.bpm.dmn.engine.DmnEngine;
import org.camunda.bpm.dmn.engine.DmnEngineConfiguration;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.model.dmn.Dmn;
import org.camunda.bpm.model.dmn.DmnModelInstance;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
@Slf4j
public class DmnServiceImpl implements DmnService {

    private DmnEngine dmnEngine;
    private DmnDecision decision;
    private DmnTemplateRepository dmnTemplateRepository;

    public DmnServiceImpl(DmnTemplateRepository dmnTemplateRepository) {
        this.dmnTemplateRepository = dmnTemplateRepository;
    }

    public void loadDecision() {
        dmnEngine = DmnEngineConfiguration.createDefaultDmnEngineConfiguration().buildEngine();
        Optional<DmnTemplate> dmnTemplateOptional =
                dmnTemplateRepository.findByTypeAndStatus(
                        Constants.DMN_RULE_TYPE, Constants.DMN_RULE_STATUS_ACTIVE);
        Assert.isTrue(dmnTemplateOptional.isPresent(), "No DMN template can be found");

        DmnModelInstance dmnModelInstance =
                Dmn.readModelFromStream(
                        IOUtils.toInputStream(dmnTemplateOptional.get().getTemplate()));
        decision = dmnEngine.parseDecision("test_rules", dmnModelInstance);
    }

    @Override
    public Long decideQuarter(Integer month) {
        if (decision == null) loadDecision();

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
