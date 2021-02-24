Feature: Import rules from excel file to camunda decision engine
  Scenario: Rules can be imported
    Given rules are ready in excel file "testExcelFiles/testRules.xlsx"
    When I upload the excel file by calling api "/admin/rules/import"
    Then I should get 200 status
    And the response message should contain "Rules are imported successfully"
