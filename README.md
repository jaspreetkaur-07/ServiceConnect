# ServiceConnect — Local Service Marketplace (Full-Stack)

A full-stack local service marketplace platform that connects customers with nearby verified service providers (plumbers, electricians, etc.). Built with Java, Spring Boot, Spring Security, PostgreSQL for the backend, and HTML/CSS/JavaScript for the frontend.

## Features

- **JWT-based Authentication** — Secure register/login with role-based access control (Customer, Worker, Admin)
- **Worker Profile Management** — Workers can create and manage their service profiles (service type, hourly rate, experience, location)
- **Geolocation-based Search** — Find nearby workers using the Haversine formula for accurate distance calculation
- **Booking Management** — Full booking lifecycle: create, accept/reject, and mark as completed
- **Review System** — Customers can rate and review workers after a completed booking
- **Interactive Frontend** — Login/Register page and a dashboard for searching workers, creating bookings, and tracking booking status in real time

## Tech Stack

**Backend:**
- Java 17+, Spring Boot 3.x
- Spring Security, JWT (JJWT library)
- PostgreSQL, Spring Data JPA / Hibernate
- Maven

**Frontend:**
- HTML5, CSS3, Vanilla JavaScript
- Fetch API for backend communication
- LocalStorage for session/token management

## Architecture

```
Frontend (HTML/CSS/JS)
        ↕ REST API calls (fetch)
Controller Layer  →  Service Layer  →  Repository Layer  →  Database
     (REST APIs)      (Business Logic)      (JPA)          (PostgreSQL)
```

## Project Structure

```
servicemarketplace/
├── src/main/java/com/sk/servicemarketplace/
│   ├── entity/       → User, Role, WorkerProfile, Booking, BookingStatus, Review
│   ├── repository/   → JPA repositories with custom queries (e.g., geolocation search)
│   ├── dto/          → Request/response objects (RegisterRequest, LoginRequest)
│   ├── security/     → JwtUtil, JwtFilter, UserDetailsServiceImpl
│   ├── config/       → SecurityConfig (JWT filter chain, CORS, password encoding)
│   ├── service/      → Business logic layer
│   └── controller/   → REST API endpoints
├── frontend/
│   ├── index.html    → Login/Register page
│   └── dashboard.html → Worker search, booking, and booking history
└── pom.xml
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

### Backend

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

### Frontend

1. Make sure the backend server is running first
2. Open `frontend/index.html` directly in a browser (double-click the file)
3. Register a new account or log in
4. You'll be redirected to `dashboard.html` where you can:
   - Search for nearby workers by service type and coordinates
   - Book a worker
   - View and track your bookings

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

- Migrate frontend to React for better state management and scalability
- Refresh token mechanism for persistent sessions
- Payment gateway integration
- Real-time notifications (WebSocket)
- Deployment on a cloud platform (Render/Railway)
- Worker-side dashboard for accepting/rejecting bookings

## Author

Built by Jaspreet Kaur as part of placement preparation, 7th semester.
