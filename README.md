# Spring R2DBC Demo 


This Demo illustrates leveraging Reactive Relational Database 
Connectivity (R2DBC) support with the application of core Spring concepts
in reactive programming model.

## To run this application
* clone this repository

* Run Postgres database using a docker container image:
```bash
$ docker run --name some-postgres -p 5432:5432 -e POSTGRES_PASSWORD=postgres -d postgres
```
* Access the database and create Employee table:
```
CREATE TABLE employee (
    id serial PRIMARY KEY,
    name character varying NOT NULL,
    salary integer NOT NULL
);
```
* Insert a couple of records in the table
```
insert into Employee(name, salary) values ('name1', 100);
insert into Employee(name, salary) values ('name2', 200);
```
* Run the application from the project's home directory
```bash
$ mvn spring-boot:run
```
* use curl to list all records
```bash
$ curl http://localhost:8080/employees
```
* Or get one specific record with Employee Id
```bash
$ curl http://localhost:8080/employees/1
```
