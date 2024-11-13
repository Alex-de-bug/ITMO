# JSF Web Application - Point Checker

## Technology Stack

### Backend
- JavaServer Faces Framework
- JPA (Java Persistence API)
- PostgreSQL Database
- Managed Beans with annotations
- Additional component libraries:
  - ICEfaces (ace)
  - PrimeFaces (p)

## Pages

### 1. Start Page
Components:
- Header with student info (Full name, group number, variant)
- Interactive clock
  - Shows current date and time
  - Updates every 13 seconds
- Navigation link to main application page

### 2. Main Application Page
Components:
- Coordinate Input Section:
  - X coordinate input
  - Y coordinate input
  - Radius input
  - Input validation for all fields
- Interactive Coordinate Plane:
  - Dynamic area visualization
  - Click-to-plot functionality
  - Color-coded points (hit/miss)
  - Auto-refresh on radius change
- Results Table:
  - History of previous checks
  - Stored in PostgreSQL Database
- Navigation link back to start page

## Technical Requirements

### Database
- PostgreSQL DBMS
- JPA for database access
- ORM provider implementation

### Server-Side Components
- Session-scoped Managed Bean for results management
- Managed Beans configuration using annotations
- Navigation rules in separate configuration file

### Application Architecture
- Two facelets templates:
  - Start page template
  - Main application template
- Managed Beans implementing server-side logic

### Validation
- Input validation for coordinate values
- Input validation for radius value
- Prevention of incorrect data input (e.g., letters in coordinate fields)

### Navigation
- Defined in separate configuration file
- Links between start and main pages