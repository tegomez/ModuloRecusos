# new feature
# Tags: optional

Feature: Load worked hours for a resource on a specific task per day

  Scenario: Successfully load worked hours
    Given the resource with LEGAJO 1 is assigned to the task with ID 1
    When 4 worked hours are loaded for the resource on the task on 2023-06-13
    Then the system should respond with the status code "200"

  Scenario: Try to load worked hours on a task not assigned to the resource
    Given the resource with LEGAJO 1 is not assigned to the task with ID 2
    When 4 worked hours are loaded for the resource on the task on "2023-06-13"
    Then the system should respond with the status code "404" and an error message

  Scenario: Try to load a negative number of worked hours
    Given the resource with LEGAJO 1 is assigned to the task with ID 1
    When -4 worked hours are loaded for the resource on the task on "2023-06-13"
    Then the system should respond with the status code "400" and an error message