# medus
Technical task for Bitė

# Main features

Created API based on provided entities

* Customer API

Method  | Path | Explanation
------------- | ------------- | ------------- |
GET  | /customers | Get all customers  |
GET  | /customers/{id} | Get customer by id |
POST  | /customers | Create customer |
PUT  | /customers/{id} | Update customer by id |
POST  | /customers/{id}/accounts | Create account for customer |
DELETE | /customers/{customerId}/accounts/{accountId} | Delete account for customer |
DELETE | /customers/{customerId} | Delete customer by id |

* Account API

Method  | Path | Explanation
------------- | ------------- | ------------- |
GET  | /accounts/{id} | Get account by id  |
PUT  | /accounts/{id} | Update account with id |
GET  | /accounts | Get all accounts |
POST | /accounts/{accountId}/phone-numbers | Create phone number for account |
DELETE | /accounts/{accountId}/phone-numbers/{id} | Delete phone number for account |

* Billable Service API

Method  | Path | Explanation
------------- | ------------- | ------------- |
GET  | /billable-services/{id} | Get billable service by id  |
GET  | /billable-services | Get all billable services  |
POST  | /billable-services | Create billable service |
DELETE  | /billable-services/{id} | Delete billable service by id  |
PUT  | /billable-services/{id} | Update billable service by id |

* Phone Number API

Method  | Path | Explanation
------------- | ------------- | ------------- |
GET  | /phone-numbers/{id} | Get phone number by id  |
GET  | /phone-numbers | Get all phone numbers  |
PUT  | /phone-numbers/{phoneId}/services/{serviceId}/order | Order service for number|
PUT  | /phone-numbers/{phoneId}/services/{serviceId} | Update ordered service for number  |
POST  | /phone-numers/{phoneId}/ordered-services/{orderedServiceId}/terminate | Terminate ordered service for phone number |

# Additional features

* Personal code for customers is validated based on Lithuanian personal code rules

* Some date validation exists for ordering services and phone numbers

* POST requests are validated

* Most of the functionality is covered by unit tests

* Initial data is loaded when starting the project

* It is possible to use docker compose to run the project

* CI exists which stores test results and stores the created artifact

# Required dependencies to run the project

* Maven (download from https://maven.apache.org/download.cgi)

* JDK 11 (download from https://adoptopenjdk.net/?variant=openjdk11)

# Unnecessary dependencies 

* Docker (download from https://www.docker.com/products/docker-desktop)

## Steps to run without docker compose

* Navigate to the root of the project

* Run "mvn clean install"

* Run "java -jar target/medus-0.0.1-SNAPSHOT.jar"

* API will become available on http://localhost:9001/

* API Swagger docs are available at http://localhost:9001/swagger-ui.html

* H2 database panel is available at http://localhost:9001/h2-console/

   * jdbc url - jdbc:h2:mem:medus
   * username - user
   * password - password

## Step to run with docker compose

* Navigate to the root of the project

* Run "mvn clean install"

* Run "docker-compose build"

* Run "docker-compose up"

* API will become available at http://localhost:9001/

* API Swagger docs are available at http://localhost:9001/swagger-ui.html

* H2 database panel is available at http://localhost:9001/h2-console/

   * jdbc url - jdbc:h2:mem:medus
   * username - user
   * password - password

## CI information

* Using Circle CI with openjdk11 docker image to run tests and store artifacts

* Build and test information is available here https://circleci.com/gh/Bipoliaras/medus



