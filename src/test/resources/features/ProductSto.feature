Feature: Product - Store
  @TestProduct
  Scenario Outline: Validacion del precio de un producto
    Given dado que estoy en la página de la tienda
    And me logueo con mi usuario "<usuario>" y clave "<contrasenia>"
    When navego a la categoria "<categoria>" y subcategoria "<subcategoria>"
    And agrego <num> unidades del primer producto al carrito
    Then valido en el popup la confirmación del producto agregado
    And valido en el popup que el monto total sea calculado correctamente
    When finalizo la compra
    Then valido el titulo de la pagina del carrito
    And vuelvo a validar el calculo de precios en el carrito
    Examples:
      | usuario                                 | contrasenia | categoria | subcategoria | num |
      | floresrafaelevelynflordemaria@gmail.com | Flores20+*+6  | Clothes   | Men          | 2   |
      | mariaflores@gmail.com                   | password123 | Clothes   | Women        | 4   |
      | floresrafaelevelynflordemaria@gmail.com | Flores20+*+6  | Autos     | Autos        | 5   |

