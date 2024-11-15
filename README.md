# Student Housing Finder System
## Comprehensive Project Documentation

## Table of Contents
1. [Project Overview](#1-project-overview)
2. [System Architecture](#2-system-architecture)
3. [Development Environment Setup](#3-development-environment-setup)
4. [Database Design](#4-database-design)
5. [Application Components](#5-application-components)
6. [Implementation Guide](#6-implementation-guide)
7. [API Documentation](#7-api-documentation)
8. [Testing Strategy](#8-testing-strategy)
9. [Deployment Guide](#9-deployment-guide)

## 1. Project Overview

### 1.1 Description
The Student Housing Finder is a web-based system designed to connect students with available housing options near their universities. The system facilitates property listings, searches, and booking management between students and landlords.

### 1.2 Core Features
- Property listing management (CRUD operations)
- Student search interface
- Booking request system
- Basic user management
- Property image handling
- REST API support

### 1.3 Target Users
- Students seeking accommodation
- Property owners/landlords
- University housing administrators

## 2. System Architecture

### 2.1 Two-App Architecture
```
App 1 (Java/Tomcat): Property Management System
        ↕
    Database (MySQL)
        ↕
App 2 (Python/Flask): Student Search System
```

### 2.2 Technology Stack
- Backend (App 1):
  - Java 8
  - Apache Tomcat 9
  - Jersey for REST API
  - MySQL Database
  - Servlet API 3.1

- Frontend (App 2):
  - Python 3.8+
  - Flask Framework
  - HTML/CSS
  - Requests library

## 3. Development Environment Setup

### 3.1 Required Software
1. Eclipse JEE 2024-09
2. MySQL Server 8.0
   - MySQL Community Server
   - MySQL Workbench
3. Apache Tomcat 9
4. Java Development Kit 8 (JDK 8)

### 3.2 Dependencies
Required JAR files (WEB-INF/lib):
- mysql-connector-j-8.0.x.jar
- jersey-bundle-1.19.4.jar
- javax.servlet-api-3.1.0.jar

### 3.3 Eclipse Configuration
1. Install Eclipse JEE 2024-09
2. Configure Tomcat:
   ```
   Window → Preferences → Server → Runtime Environments
   Add → Apache Tomcat 9.0
   Browse to Tomcat installation directory
   ```

### 3.4 Project Setup
1. Create Dynamic Web Project:
   ```
   File → New → Dynamic Web Project
   Name: StudentHousingFinder
   Target Runtime: Apache Tomcat 9.0
   ```

2. Project Structure:
   ```
   StudentHousingFinder/
   ├── src/
   │   └── main/
   │       └── java/
   │           └── com/
   │               └── housing/
   │                   ├── model/
   │                   ├── dao/
   │                   └── api/
   └── WebContent/
       ├── WEB-INF/
       │   ├── lib/
       │   └── web.xml
       └── css/
   ```

## 4. Database Design

### 4.1 Database Schema
```sql
CREATE DATABASE student_housing;
USE student_housing;

-- Properties Table
CREATE TABLE properties (
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    description TEXT,
    price DECIMAL(10,2) NOT NULL,
    rooms INT NOT NULL,
    address VARCHAR(255) NOT NULL,
    neighborhood VARCHAR(100),
    contact_phone VARCHAR(20) NOT NULL,
    available BOOLEAN DEFAULT true,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Bookings Table
CREATE TABLE bookings (
    id INT PRIMARY KEY AUTO_INCREMENT,
    property_id INT,
    student_name VARCHAR(100) NOT NULL,
    student_phone VARCHAR(20) NOT NULL,
    status ENUM('pending', 'approved', 'rejected') DEFAULT 'pending',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (property_id) REFERENCES properties(id)
);
```

### 4.2 Sample Data
```sql
INSERT INTO properties (
    title, 
    description, 
    price, 
    rooms, 
    address, 
    neighborhood,
    contact_phone
) 
VALUES 
('Studio proche ENIT', 'Studio meublé, cuisine équipée', 400.00, 1, 'Rue des Ingénieurs', 'El Manar', '51234567'),
('Appartement près de FST', 'Chambre dans appartement partagé', 300.00, 1, 'Avenue de la Faculté', 'Centre Urbain Nord', '22334455');
```

## 5. Application Components

### 5.1 Core Java Classes

#### Database Connection
```java
public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/student_housing";
    private static final String USER = "root";
    private static final String PASS = "your_password";
    
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
```

#### Property Model
```java
public class Property {
    private int id;
    private String title;
    private String description;
    private double price;
    private int rooms;
    private String address;
    private String neighborhood;
    private String contactPhone;
    private boolean available;
    
    // Getters and Setters
}
```

### 5.2 API Resources
- PropertyResource: Handles property-related endpoints
- BookingResource: Manages booking requests
- SearchResource: Handles property search functionality

## 6. Implementation Guide

### 6.1 Two-Week Implementation Plan

#### Week 1: Java Backend (App 1)
- Day 1-2: Setup & Database
  - Environment setup
  - Database creation
  - Project structure setup

- Day 3-4: Core Implementation
  - Model classes
  - DAO layer
  - Basic API endpoints

- Day 5: Initial Testing
  - API endpoint testing
  - Database connectivity
  - Basic error handling

#### Week 2: Python Frontend (App 2)
- Day 1-2: Frontend Setup
  - Flask application setup
  - Basic templates
  - API integration

- Day 3-4: UI Implementation
  - Search interface
  - Property listings
  - Booking forms

- Day 5: Integration & Testing
  - End-to-end testing
  - Bug fixes
  - Documentation

## 7. API Documentation

### 7.1 Available Endpoints

#### Properties API
```
GET    /api/properties         - List all properties
GET    /api/properties/{id}    - Get specific property
POST   /api/properties         - Add new property
PUT    /api/properties/{id}    - Update property
DELETE /api/properties/{id}    - Delete property
```

#### Bookings API
```
POST   /api/bookings          - Create booking request
GET    /api/bookings/{id}     - Get booking status
PUT    /api/bookings/{id}     - Update booking status
```

### 7.2 Sample API Responses
```json
{
    "id": 1,
    "title": "Studio proche ENIT",
    "price": 400.00,
    "rooms": 1,
    "address": "Rue des Ingénieurs",
    "neighborhood": "El Manar",
    "contact_phone": "51234567"
}
```

## 8. Testing Strategy

### 8.1 Database Testing
- Connection testing
- CRUD operations verification
- Data integrity checks

### 8.2 API Testing
- Endpoint functionality
- Response format validation
- Error handling

### 8.3 Integration Testing
- Frontend-backend integration
- Search functionality
- Booking process

## 9. Deployment Guide

### 9.1 Prerequisites
- JDK 8 installed
- Tomcat 9 configured
- MySQL Server running
- Python 3.8+ (for App 2)

### 9.2 Deployment Steps
1. Database Setup
   - Run schema creation script
   - Import sample data
   
2. Java Application (App 1)
   - Build WAR file
   - Deploy to Tomcat
   - Verify database connection
   
3. Python Application (App 2)
   - Set up virtual environment
   - Install dependencies
   - Configure API endpoints
   - Start Flask server

### 9.3 Testing Deployment
1. Verify Tomcat startup
2. Check database connectivity
3. Test API endpoints
4. Validate frontend functionality

🤝 Contributing
This is a student project, but suggestions and improvements are welcome! Feel free to:

1. Fork the repository
2. Create a feature branch
3. Submit a pull request
