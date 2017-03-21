Feature: Web Search
  In order to get the details
  As a normal user
  I want to be able to perform web search

  @web
  Scenario: Movie Search with Title
    Given I am on Google homepage
    When I search for 'duckduckgo'
    Then title should contain text 'DuckDuckGo'

  @web
  Scenario: Movie Search with Title1
    Given I am on Google homepage
    When I search for 'duckduckgo'
    Then title should contain text 'fail DuckDuckGo'