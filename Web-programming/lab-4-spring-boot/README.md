# Web Application Project

## Technology Stack

### Backend
- Spring Framework
- Spring Data JPA
- Oracle Database
- REST API

### Frontend
- React + Redux
- ES6 + JSX
- PrimeReact Component Library

## Responsive Design
Application supports three display modes:
- Desktop (≥1259px)
- Tablet (832px - 1258px)
- Mobile (<832px)

## Pages

### 1. Start Page (Login)
Components:
- Header with student info (Full name, group number, variant)
- Login form
  - Username field
  - Password field (stored as hash in database)
- Authentication required to access main page

### 2. Main Application Page
Components:
- Coordinate Input Section:
  - X-coordinate: MultiSelect (-3,-2,-1,0,1,2,3,4,5)
  - Y-coordinate: Slider (-3 to 5)
  - Radius: MultiSelect (-3,-2,-1,0,1,2,3,4,5)
  - Input validation for all fields
- Interactive Coordinate Plane:
  - Dynamic area visualization
  - Click-to-plot functionality
  - Color-coded points (hit/miss)
  - Auto-refresh on radius change
- Results Table:
  - History of previous attempts
  - Stored in Oracle Database
- Logout Link:
  - Returns to start page
  - Closes user session

## Technical Requirements

### Database
- Oracle DBMS
- Spring Data for database access
- Tables:
  - Users (login credentials)
  - Results (coordinate checks)

### Security
- Authentication required
- Password hashing
- Session management

### API
- REST API for backend-frontend communication
- Secure endpoints
- JSON data format