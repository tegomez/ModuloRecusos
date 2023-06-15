Feature: Carga de horas

  Scenario: Carga de horas satisfactoria
    Given un empleado con el número de legajo 123456
    And una tarea con el identificador 789
    When se carga correctamente la cantidad de 8 horas para la tarea en la fecha "2023-06-15"
    Then se registra la carga de horas exitosamente