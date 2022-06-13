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
* CI exists which stores test results and stores the created artifact
* API Swagger docs are available at http://localhost:8081/swagger-ui.html

## Steps to run without docker compose

* Navigate to the root of the project

* Run "mvn clean install"

* Run "docker-compose build"

* Run "docker-compose up"

* API will become available at http://localhost:8081/

* API Swagger docs are available at http://localhost:8081/swagger-ui.html
