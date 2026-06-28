# Player Performance Data Engine & REST API

A Spring Boot backend application built to process raw data streams, manage player performance profiles in a relational database, and expose a complete RESTful API. 

This project was developed from the ground up utilizing a clean, layered architecture pattern (Controller -> Service -> Repository) matching industry-standard data-handling practices.

---

## 🚀 Features

* **Custom CSV Data Stream Parsing:** Implements raw string manipulation to process incoming data streams line-by-line without relying on external third-party CSV parser libraries.
* **Automated System Initialization:** Features a component that automatically parses a local dataset on startup, persists it to the database, and outputs targeted data summaries directly to `STDOUT`.
* **Full CRUD REST API:** Formulated with clean, resource-oriented endpoint mappings to fully create, read, update, and delete player profiles dynamically.

---

## 🛠️ Tech Stack

* **Backend Framework:** Java 17 / Spring Boot 3.x
* **Data Access Layer:** Spring Data JPA (Hibernate)
* **Database Engine:** PostgreSQL / H2 (Local Development)
* **Build Automation:** Maven

---

## 📖 Design Choices & Architecture

### 1. Zero-Dependency Stream Processing
To deeply understand custom file IO and string optimization, standard parsing utilities were intentionally omitted. The custom parsing algorithm splits text data records using structured delimiters (`\\r?\\n` and `,`), sanitizes trailing white spaces, handles malformed row exceptions gracefully, and maps fields securely into strong-typed JPA entities.

### 2. Scalable Tie-Breaking Business Logic
When handling tie scores among multiple records, the application pushes heavy calculations directly down to the database engine. It evaluates maximum thresholds via optimized queries and sorts duplicate values **alphabetically** at the data layer using explicit database clauses (`ORDER BY s.firstName ASC`), ensuring blazing fast response times.

### 3. Fault-Tolerant Layer Routing
To maximize system uptime, Java's `Optional<T>` API combined with `.orElseThrow()` conditional branching is utilized across the Service Layer. This prevents native null pointer crashes and creates distinct boundaries for handling missing resources cleanly.

---

## 🛣️ API Endpoints

All endpoints are hosted relative to the base context path: `http://localhost:8080/api/v1/players`

| Method | Endpoint | Description | Query Parameters / Payload |
| :--- | :--- | :--- | :--- |
| **GET** | `/` | Retrieve all top-scoring records sorted alphabetically | None |
| **GET** | `/search` | Fetch a player's profile by their first name | `?firstName=George` |
| **POST** | `/` | Add a new player record to the database | JSON Body: `{"firstName": "Sipho", "score": 85}` |
| **PUT** | `/{playerId}` | Update an existing player's name details | Optional Params: `?firstName=Sipho&secondName=Zwane` |
| **DELETE**| `/{playerId}` | Permanently drop a record from the database by ID | Path Variable: `id` |

---

## 🔒 Security & Cloud Deployment Strategy

### Security Strategy
In a production deployment, all state-changing operations (`POST`, `PUT`, `DELETE`) are designed to be blocked behind a security filter chain using **Spring Security**. Access is granted using stateless **JWT (JSON Web Tokens)** validation accompanied by **Role-Based Access Control (RBAC)**, keeping data mutation privileges strictly constrained to authorized administrators.

### Cloud Architecture Blueprint
* **Compute:** Containerize the Spring Boot application using a multi-stage **Docker** build and deploy it to a scalable platform like **AWS Elastic Beanstalk** or **AWS ECS (Fargate)**.
* **Database:** Connect the application layer to a managed **AWS RDS (PostgreSQL)** database instance featuring Multi-AZ replication for automated backups and high availability.
* **User Interface:** Host a frontend application (e.g., React or Angular) statically inside an **AWS S3 Bucket** served worldwide through **AWS CloudFront** edge locations.

---

## 🏃 Getting Started & How to Run

1. **Clone the Project:**
   ```bash
   git clone <your-repository-url>
   cd <project-directory-name>
