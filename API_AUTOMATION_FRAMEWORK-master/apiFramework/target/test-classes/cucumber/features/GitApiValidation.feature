#Author: phalguna.mech@gmail.com
@GitApiFeature
Feature: To validate the public APIs of GitHub

  @GitApiFeatureS1
  Scenario Outline: To validate the total number of repositories exist for given programming languages
    Given user set the API endpoint url to fetch "repositories"
    And user set the API endpoint query parameters to fetch records for "<progLang>" programming language
    And user set the GET request header "headerAcceptRepoSearch"
    When user execute the GET request on GitHub
    Then user recieves response code "200"
    And user recieves total number of repositories count as "<recordCount>" for language "<progLang>"

    Examples: 
      | progLang    | recordCount |
      | java        |      111111 |
      | python      |       12313 |
      | java;python |       12313 |

  @GitApiFeatureS2
  Scenario Outline: To validate the total number of repositories exist for a given user
    Given user set the API endpoint url to fetch "users"
    And user set the API endpoint query parameters to fetch records for "<user>" username
    And user set the GET request header "headerAcceptRepoSearch"
    When user execute the GET request on GitHub
    Then user recieves response code "200"
    And user recieves total number of repositories count as "<recordCount>" for username "<user>"

    Examples: 
      | user     | recordCount |
      | phalguna |      111111 |
      | martin   |      123123 |

  @GitApiFeatureS3
  Scenario Outline: To validate the most starred Github repositories in decreasing order
    Given user set the API endpoint url to fetch "repositories"
    And user set the API endpoint query parameters to fetch top 10 starred records for "<user>" username
    And user set the GET request header "headerAcceptRepoSearch"
    When user execute the GET request on GitHub
    Then user recieves response code "200"
    And user verifies top 10 record details

    Examples: 
      | user     |
      | phalguna |
      | martin   |

  @GitApiFeatureS4
  Scenario Outline: To validate total repositories created on a particular date for a paricular user
    Given user set the API endpoint url to fetch "repositories"
    And user set the API endpoint query parameters to fetch repositories created on "<date>" by "<user>" username
    And user set the GET request header "headerAcceptRepoSearch"
    When user execute the GET request on GitHub
    Then user recieves response code "200"
    And user verifies record details of repositories created on a particular day

    Examples: 
      | user | date       |
      | rao  | 2020-05-16 |

  @GitApiFeatureS5
  Scenario Outline: To validate the list of commits on a particular day by an user
    Given user set the API endpoint url to fetch "commits"
    And user set the API endpoint query parameters to fetch commits on "<date>" created by "<user>" username
    And user set the GET request header "headerAcceptCommitSearch"
    When user execute the GET request on GitHub
    Then user recieves response code "200"
    And user verifies record details of commits of a user on a particular day

    Examples: 
      | user  | date       |
      | johny | 2020-05-16 |
