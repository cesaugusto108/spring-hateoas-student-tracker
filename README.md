# spring-hateoas-student-tracker

RESTful API using Spring HATEOAS. GET, POST, PUT, PATCH, DELETE HTTP requests

## GET HTTP request

    > $ : curl -v http://localhost:8080/studenttracker/students/1 | json_pp
    > 
    > GET /guests/1 HTTP/1.1
    > 
    > Host: localhost:8080
    > 
    > User-Agent: curl/7.74.0
    > 
    > HTTP/1.1 200
    > 
    > Content-Type: application/hal+json
    > 
    > {
    >     "id": 1,
    >     "name": {
    >         "firstName": "Talita",
    >         "lastName": "Silva"
    >     },
    >     "email": "talita@email.com",
    >     "registration": "202100078111",
    >     "addresses": [
    >         {
    >             "id": 2,
    >             "street": "Rua Laranjeiras",
    >             "number": "1293",
    >             "city": "Aracaju"
    >         }
    >     ],
    >     "undergraduatePrograms": [
    >         {
    >             "id": 2,
    >             "description": "Psicologia",
    >             "campus": {
    >                 "id": 1,
    >                 "description": "São Cristóvão"
    >             }
    >         }
    >     ],
    >     "links": [
    >         {
    >             "rel": "self",
    >             "href": "http://localhost:8080/studenttracker/students/1"
    >         },
    >         {
    >             "rel": "update",
    >             "href": "http://localhost:8080/studenttracker/students/1/update"
    >         },
    >         {
    >             "rel": "delete",
    >             "href": "http://localhost:8080/studenttracker/students/1/delete"
    >         },
    >         {
    >             "rel": "students",
    >             "href": "http://localhost:8080/studenttracker/students/?page=0&max=5"
    >         }
    >     ]

> }
