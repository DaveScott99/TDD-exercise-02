# TDD-exercise-02

Este é um sistema de eventos e cidades com uma relação N-1 entre eles:

![image](https://github.com/DaveScott99/TDD-exercise-02/assets/101915085/e34c0480-ede2-443c-a2ef-59b6845ab86b)

## Testes de integração realizados

  - CityControllerIT
    - findAllShouldReturnAllResourcesSortedByName
    - insertShouldInsertResource
    - deleteShouldReturnNoContentWhenIndependentId
    - deleteShouldReturnNotFoundWhenNonExistingId
    - deleteShouldReturnBadRequestWhenDependentId
      
  - EventControllerIT
    -  updateShouldUpdateResourceWhenIdExists
    -  updateShouldReturnNotFoundWhenIdDoesNotExist
