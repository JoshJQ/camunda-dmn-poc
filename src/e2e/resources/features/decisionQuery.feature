Feature: Query the decision result
  Scenario Outline: Get the quarter with a month
    Given I have a month
    When I check with api "/decision/quarter/<month>"
    Then I should get 200 status
    And the response message should contain "Month <month> is in quarter <quarter>"
  Examples:
    | month | quarter |
    | 1     | 1       |
    | 2     | 1       |
    | 3     | 1       |
    | 4     | 2       |
    | 5     | 2       |
    | 6     | 2       |
    | 7     | 3       |
    | 8     | 3       |
    | 9     | 3       |
    | 10    | 4       |
    | 11    | 4       |
    | 12    | 4       |