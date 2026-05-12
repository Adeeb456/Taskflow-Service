# TaskFlow Service

A production-style Task Management application built using Java Spring Boot.

The application allows users to create, update, manage, and track tasks through secure REST endpoints.

---

# Tech Stack
* HTML, CSS, Javascript, Bootstrap, Anime.js (For Frontend)
* Java 17
* Spring Boot 3
* Spring Web
* Spring Data JPA
* Spring Security
* H2 Database (In-memory Database, No need to set-up any config for this)
* Maven
* Bean Validation
* Postman
* Junit for unit testing

---

# Features

* Create Tasks
* Fetch All Tasks
* Fetch Task By ID
* Update Existing Tasks
* Delete Tasks
* Mark Task As In Progress
* Mark Task As Completed
* Centralized Exception Handling
* Input Validation
* Basic Authentication
* Layered Architecture
* DTO-Based API Design

---

# Project Structure

```text
src/main/java/com/adeeb/taskflow/taskflow_service
│
├── controller
├── service
├── repository
├── entity
├── dto
│   ├── request
│   └── response
├── security
├── exception
├── util
│   └── mapper
└── enums
```

---

# Prerequisites

Make sure the following are installed:

* Java 17+
* Maven 3+
* IDE (IntelliJ IDEA recommended)

---

# Clone The Repository

```bash
git clone https://github.com/Adeeb456/Taskflow-Service.git
cd taskflow-service
```

---

# Configure Application

Update the `application.properties` file if required.

Example:

```properties
spring.h2.console.enabled=true
spring.jpa.hibernate.ddl-auto=update

Set these in environment variables for secure set-up

app.security.username=admin
app.security.password=admin
```

---

# Run The Application

Using Maven:

```bash
mvn spring-boot:run
```

Or run the main class directly from the IDE.

Application will start on:

```text
http://localhost:8080
or
http://localhost:8080/index.html
```
A landing page as shown below will appear:

<img width="1907" height="815" alt="image" src="https://github.com/user-attachments/assets/922ffd20-8c80-454e-bdff-de01621e1fc7" />



Click on "Start Managing Tasks" button to see the task interface.
Task interface as shown below will appear:

<img width="1881" height="817" alt="image" src="https://github.com/user-attachments/assets/d6ce2169-d395-4f67-9335-fe13a92ef512" />





---

# Authentication

The API uses Basic Authentication.

Use the same credentials that you have set in application.properties file.
For example, you have set both username and password as "admin", then use those here:

```text
Username: admin
Password: admin
```

<img width="465" height="365" alt="image" src="https://github.com/user-attachments/assets/89d1e80e-3a5b-4ec7-8f1b-248c0bb476d0" />




---

# H2 Database Console

Access H2 console at:

```text
http://localhost:8080/h2-console
```

Just click on connect, any password is not required.

<img width="1199" height="743" alt="image" src="https://github.com/user-attachments/assets/cb4e01ba-8673-49e5-9ddb-2ed6cb34ef30" />



H2-DB console will show all schemas and related queries as shown below:

<img width="1894" height="694" alt="image" src="https://github.com/user-attachments/assets/98f381c9-8184-48b6-bd8f-feab03b46632" />




---

# API Endpoints

## Get All Tasks

```http
GET /api/v1/tasks
```

---

## Get Task By ID

```http
GET /api/v1/tasks/{taskId}
```

---

## Create Task

```http
POST /api/v1/tasks
```

Request Body:

```json
{
  "title": "Prepare project documentation",
  "description": "Complete README and API documentation",
  "dueDate": "2026-05-20"
}
```

---

## Update Task

```http
PUT /api/v1/tasks/{taskId}
```

Request Body:

```json
{
  "title": "Updated task title",
  "description": "Updated description",
  "dueDate": "2026-05-25"
}
```

---

## Delete Task

```http
DELETE /api/v1/tasks/{taskId}
```

---

## Start Task

```http
PATCH /api/v1/tasks/{taskId}/start
```

---

## Mark Task As Completed

```http
PATCH /api/v1/tasks/{taskId}/mark-completed
```

---

# Validation Rules

* Title is mandatory
* Title length must be within allowed limit
* Due date cannot be null
* Due date cannot be in the past

---

# Exception Handling

The application uses centralized exception handling through `@RestControllerAdvice`.

Handled exceptions include:

* Task Not Found
* Invalid Task State
* Validation Errors
* Database Errors
* Invalid JSON Request
* Generic Server Errors

---

# Testing The APIs

You can test the APIs using:

* Postman
* cURL
* Frontend UI
* IntelliJ HTTP Client

Example cURL request:

```bash
curl -u admin:admin123 http://localhost:8080/api/v1/tasks
```

---

# Architecture Highlights

* Layered Architecture
* DTO-Based Communication
* Utility-Based Mapping
* Clean Separation Of Concerns
* RESTful API Design
* Stateless Authentication

---

# Author

Adeeb Khan

Software Engineer | Java Backend Developer
