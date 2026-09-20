@api @products
Feature: Products API
  As an API consumer
  I want to list, filter, sort and fetch products
  So that I can build a catalog on top of the service

  @smoke
  Scenario: The first page of products includes pagination metadata
    When I send a GET request to "/api/products"
    Then the response status should be 200
    And the JSON array "data" should have 10 items
    And the JSON field "meta.page" should equal "1"
    And the JSON field "meta.limit" should equal "10"
    And the JSON field "meta.total" should equal "12"
    And the JSON field "meta.totalPages" should equal "2"

  @regression
  Scenario: The second page contains the remaining products
    When I send a GET request to "/api/products?page=2&limit=10"
    Then the response status should be 200
    And the JSON field "meta.page" should equal "2"
    And the JSON array "data" should have 2 items

  @regression
  Scenario: A page beyond the last returns no products
    When I send a GET request to "/api/products?page=3&limit=10"
    Then the response status should be 200
    And the JSON array "data" should have 0 items

  @regression
  Scenario: Products can be filtered by category
    When I send a GET request to "/api/products?category=Accessories"
    Then the response status should be 200
    And the JSON array "data" should have 3 items
    And every item in "data" should have "category" equal to "Accessories"

  @regression
  Scenario: Products can be filtered to those in stock
    When I send a GET request to "/api/products?inStock=true"
    Then the response status should be 200
    And every item in "data" should have "stock" greater than 0

  @regression
  Scenario Outline: Products can be sorted by price
    When I send a GET request to "/api/products?sort=<sort>"
    Then the response status should be 200
    And the "price" values in "data" should be sorted "<direction>"

    Examples:
      | sort       | direction  |
      | price_asc  | ascending  |
      | price_desc | descending |

  @smoke
  Scenario: A product can be fetched by ID
    When I send a GET request to "/api/product?id=1"
    Then the response status should be 200
    And the JSON field "data.id" should equal "1"
    And the JSON field "data.name" should equal "ApexBook Pro 14"
    And the JSON field "data.brand" should equal "Apex"
    And the JSON field "data.category" should equal "Laptops"
    And the JSON field "data.price" should equal "1299"
    And the JSON field "data.stock" should equal "12"

  @regression
  Scenario Outline: Special prices are returned exactly as stored
    When I send a GET request to "/api/product?id=<id>"
    Then the response status should be 200
    And the JSON field "data.price" should equal "<price>"

    Examples:
      | id | price  | note              |
      | 8  | 19.999 | raw decimal price |
      | 7  | 0      | free item         |

  @regression
  Scenario: An unknown product ID returns 404
    When I send a GET request to "/api/product?id=99999"
    Then the response status should be 404
    And the JSON field "error" should equal "PRODUCT_NOT_FOUND"

  @regression
  Scenario: A missing product ID is rejected
    When I send a GET request to "/api/product"
    Then the response status should be 400
    And the JSON field "error" should equal "INVALID_ID"
