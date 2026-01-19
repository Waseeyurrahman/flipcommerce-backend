# FlipCommerce Backend 🛒

A **production-ready Spring Boot e-commerce backend** providing RESTful APIs for managing sellers, products, customers, carts, and orders.  
The project follows a clean layered architecture and includes email notifications, global exception handling, and Swagger-based API testing.

---

## 🚀 Features

- 🧩 Layered architecture (Controller → Service → Repository)
- 🛍️ Seller & Product management
- 👤 Customer, Cart & Order workflows
- 💳 Card validation and checkout flow
- 📧 Email notifications on order placement & cart checkout
- ⚠️ Global exception handling using `@ControllerAdvice`
- 📊 Analytics APIs (top products, sellers, etc.)
- 📄 Swagger/OpenAPI integration for API testing
- 🧪 Tested end-to-end using Swagger UI

---

## 🛠️ Tech Stack

- **Java 17**
- **Spring Boot**
- Spring Web
- Spring Data JPA (Hibernate)
- MySQL
- Maven
- Swagger / OpenAPI
- JavaMailSender (Email)
- Lombok

---

## 📂 Project Structure

src/main/java/com/example/FlipCommerce
│
├── controller # REST Controllers
├── service # Business Logic
├── repository # Data Access Layer
├── model # JPA Entities
├── dto # Request & Response DTOs
├── transformer # DTO ↔ Entity Mappers
├── exception # Custom & Global Exceptions
└── Enum # Enums (Category, CardType, etc.)


---

## 🔑 Core APIs Overview

### Seller
- Add seller
- Update seller by email
- Sellers by product category
- Seller with max/min products

### Product
- Add product
- Products by category
- Top 5 cheapest / costliest products
- Out-of-stock products
- Products by seller

### Customer & Cart
- Add customer
- Add items to cart
- Checkout cart

### Order
- Place direct order
- Place order from cart
- Email notification on successful order

---

## 📧 Email Notification

- Email is sent on:
  - Successful **direct order**
  - Successful **cart checkout**
- Implemented using `JavaMailSender`

---

## ⚠️ Global Exception Handling

Centralized exception handling implemented using `@ControllerAdvice` to provide:
- Clean JSON error responses
- Proper HTTP status codes
- Better Swagger visibility

---

## 📑 Swagger API Documentation

After running the application, access Swagger UI at:

http://localhost:8080/swagger-ui/index.html


Use Swagger to:
- Test all APIs
- Validate request/response flows
- Verify end-to-end functionality

---

## ▶️ How to Run

1. Clone the repository
   ```bash
   git clone https://github.com/Waseeyurrahman/flipcommerce-backend.git
Configure database & email settings in application.properties

Run the application

mvn spring-boot:run


Open Swagger UI and test APIs



👤 Author

Waseeyur Rahman
Backend Developer | Spring Boot | Java
