package com.db.camunda.repository;

import com.db.camunda.entity.DmnTemplate;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DmnTemplateRepository extends JpaRepository<DmnTemplate, Long> {
    Optional<DmnTemplate> findByTypeAndStatus(String type, String status);
}
