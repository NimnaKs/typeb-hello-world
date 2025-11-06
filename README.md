# 🧩 TypeB HelloWorld API

A minimal Spring Boot REST API that greets users based on validated input.  
Built as part of the **TypeB Digital backend assignment**.

---

## 🚀 Overview

This project exposes a single endpoint `/hello-world` that returns a greeting message if the name starts with letters **A–M**, and a validation error otherwise.  
It includes a clean controller-service structure, global exception handling, Swagger documentation, and tests.

---

## ⚙️ Run the Application
```bash
mvn spring-boot:run
```

**Access the API:**
* Swagger UI: http://localhost:8080/swagger-ui/index.html
* API JSON: http://localhost:8080/v3/api-docs

**Example:**
```bash
GET /hello-world?name=Alice
Response: { "message": "Hello Alice" }
```

---

## 🧪 Testing
```bash
mvn test
```

**Includes:**
* `HelloServiceImplTest` – validates service logic
* `HelloControllerTest` – checks API responses

---

## 🛠️ Tech Stack

* Java 17
* Spring Boot 3.4.11
* Springdoc OpenAPI (Swagger)
* JUnit 5 & Mockito

---

## 👨‍💻 Author

**Nimna Kaveesha**  
Backend Developer
📧 nimnakse@gmail.com