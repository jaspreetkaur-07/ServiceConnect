# ServiceConnect — Local Service Marketplace Backend

A full-stack backend REST API for a local service marketplace platform that connects customers with nearby verified service providers (plumbers, electricians, etc.). Built with Java, Spring Boot, Spring Security, and PostgreSQL.

## Features

- **JWT-based Authentication** — Secure register/login with role-based access control (Customer, Worker, Admin)
- **Worker Profile Management** — Workers can create and manage their service profiles (service type, hourly rate, experience, location)
- **Geolocation-based Search** — Find nearby workers using the Haversine formula for accurate distance calculation
- **Booking Management** — Full booking lifecycle: create, accept/reject, and mark as completed
- **Review System** — Customers can rate and review workers after a completed booking

## Tech Stack

- **Backend:** Java 17+, Spring Boot 3.x
- **Security:** Spring Security, JWT (JJWT library)
- **Database:** PostgreSQL, Spring Data JPA / Hibernate
- **Build Tool:** Maven
- **Testing:** Postman (manual API testing)

## Architecture

```
Controller Layer  →  Service Layer  →  Repository Layer  →  Database
     (REST APIs)      (Business Logic)      (JPA)          (PostgreSQL)
```

## Project Structure

```
com.sk.servicemarketplace/
├── entity/       → User, Role, WorkerProfile, Booking, BookingStatus, Review
├── repository/   → JPA repositories with custom queries (e.g., geolocation search)
├── dto/          → Request/response objects (RegisterRequest, LoginRequest)
├── security/     → JwtUtil, JwtFilter, UserDetailsServiceImpl
├── config/       → SecurityConfig (JWT filter chain, password encoding)
├── service/      → Business logic layer
└── controller/   → REST API endpoints
```

## API Endpoints

### Authentication (`/api/auth`) — Public
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/register` | Register a new user (Customer/Worker) |
| POST | `/login` | Authenticate and receive JWT token |

### Worker Profiles (`/api/workers`) — Requires JWT
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/profile` | Create/update worker profile |
| GET | `/search` | Search nearby workers by location & service type |
| GET | `/all` | List all worker profiles |

### Bookings (`/api/bookings`) — Requires JWT
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/create` | Customer creates a booking with a worker |
| PUT | `/{id}/status` | Update booking status (ACCEPTED/REJECTED/COMPLETED) |
| GET | `/my-bookings` | Get bookings as a customer |
| GET | `/assigned-to-me` | Get bookings as a worker |

### Reviews (`/api/reviews`) — Requires JWT
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/add` | Add a review for a completed booking |

## Setup & Run Locally

1. Clone the repository:
   ```
   git clone https://github.com/jaspreetkaur-07/ServiceConnect.git
   ```
2. Create a PostgreSQL database named `service_marketplace`
3. Update `src/main/resources/application.properties` with your database credentials
4. Run the application:
   ```
   mvn spring-boot:run
   ```
5. Server starts at `http://localhost:8080`
6. Test endpoints using Postman — set `Authorization: Bearer <token>` for protected routes

## Sample Request

**Register:**
```json
POST /api/auth/register
{
    "name": "John Doe",
    "email": "john@example.com",
    "password": "securepass123",
    "phone": "9876543210",
    "role": "CUSTOMER"
}
```

**Response:**
```json
{
    "token": "eyJhbGciOiJIUzI1NiJ9...",
    "message": "Registration successful"
}
```

## Future Enhancements

- Frontend (React) for a complete full-stack experience
- Refresh token mechanism for persistent sessions
- Payment gateway integration
- Real-time notifications (WebSocket)
- Deployment on a cloud platform (Render/Railway)

## Author

Built by Jaspreet Kaur as part of placement preparation, 7th semester.
