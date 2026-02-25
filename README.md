# Distributed Assessment Platform (Microservices Architecture)

A scalable quiz and assessment engine built using Spring Boot microservices.  
The system supports dynamic quiz generation, secure authentication, distributed service communication, and centralized exception handling.

---

## Overview

Users can:

- Select quiz category (e.g., Java, C++, etc.)
- Choose number of questions
- Attempt multiple-choice questions
- Submit quiz and receive instant scoring

The platform follows a microservices architecture with service discovery and API gateway for scalable request routing.

---

## Architecture

The system consists of:

- **API Gateway** – Centralized request routing
- **Quiz Service** – Quiz generation, submission, and scoring
- **Question Service** – External API integration and persistence
- **Eureka Server** – Service discovery and registration

Inter-service communication is implemented using **OpenFeign** with client-side load balancing.

## Architecture Diagram

<p align="center">

<pre>
                 ┌────────────────────┐
                 │       Client       │
                 └─────────┬──────────┘
                           │
                           ▼
                 ┌────────────────────┐
                 │     API Gateway    │
                 └─────────┬──────────┘
                           │
        ┌──────────────────┴──────────────────┐
        ▼                                     ▼
┌────────────────────┐              ┌────────────────────┐
│    Quiz Service    │              │  Question Service  │
└─────────┬──────────┘              └─────────┬──────────┘
          │                                     │
          ▼                                     ▼
  ┌────────────────┐                  ┌────────────────┐
  │  PostgreSQL DB │                  │  External API  │
  └────────────────┘                  └────────────────┘

                 ┌────────────────────┐
                 │    Eureka Server   │
                 └────────────────────┘
</pre>

</p>

---

## Security

- Spring Security
- JWT-based authentication
- BCrypt password encryption
- Role-based access control

---

## External API Integration

- Fetches categorized MCQs from a third-party API
- Maps API responses to DTOs
- Persists data into PostgreSQL using Spring Data JPA

---

## Exception Handling

- Implemented centralized exception handling using `@RestControllerAdvice`
- Created custom exception classes (e.g., `ResourceNotFoundException`)
- Standardized API error responses for consistency and reliability
- Maintained a dedicated exception handling layer

---

## Tech Stack

- Java
- Spring Boot
- Spring Cloud (OpenFeign, Eureka)
- Spring Security
- PostgreSQL
- Docker
- Maven

---

## Key Features

- Microservices architecture
- Service discovery & load balancing
- Secure authentication using JWT
- External API data ingestion
- RESTful API design
- Centralized exception handling
- Layered architecture (Controller → Service → Repository)

---

## How to Run

Start services in the following order:

1. Eureka Server  
2. Question Service  
3. Quiz Service  
4. API Gateway  
