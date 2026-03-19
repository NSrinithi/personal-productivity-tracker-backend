# 📊 Study Tracker Backend

> 📊 Backend system that processes study data and generates real-time insights like streaks, analytics, and summaries.
> 🚀 Built as part of backend learning journey focusing on real-world data handling and analytics.

---

## 🚀 Features

- 🔐 User Authentication using JWT
- 📅 Track daily study logs
- 📊 Weekly and Monthly summaries
- 🔥 Study streak tracking (current & longest)
- 📂 Category-wise study analysis
- 📈 Dashboard with analytics
- 📄 Pagination support for logs
- 🧾 Clean REST APIs
- ⚡ Optimized data aggregation using Java Streams

---

## 🛠 Tech Stack

- **Backend:** Java, Spring Boot  
- **Security:** Spring Security (JWT)  
- **Database:** MySQL  
- **ORM:** JPA (Hibernate)

---

## 📌 Key Functionalities

### 🔐 Authentication
- User Registration
- Login with JWT token

### 📘 Study Logs
- Add study logs
- Update & delete logs
- Fetch logs by date or category

### 📊 Dashboard
- Today's study status
- Total hours studied
- Category-based breakdown
- Weekly & Monthly insights

### 🔥 Streak Tracking
- Current streak
- Longest streak
- Last studied date

---

## 📡 Sample APIs

### Auth APIs
- `POST /auth/register`
- `POST /auth/login`

### Study APIs
- `POST /study`
- `GET /study`
- `GET /study/date?date=YYYY-MM-DD`
- `DELETE /study/{id}`

### Dashboard API
- `GET /dashboard`

---

## ⚙️ Setup Instructions

1. Clone the repository
2. Configure MySQL in `application.properties`
3. Run the application
   
---

## 📈 Future Improvements

- Redis caching for dashboard performance
- Role-based access control
- Frontend integration (React)
- Docker deployment

---

## ⚙️ How It Works

- Users log daily study activities
- Data is stored and processed using backend services
- Streaks are calculated based on continuous study days
- Weekly and monthly summaries are generated using aggregation logic
- Dashboard provides insights into study patterns and performance

---

## 💡 Key Learnings

- Implemented JWT-based authentication
- Designed backend services for analytics and aggregation
- Improved understanding of clean architecture and DTO usage

---

## 🙋‍♂️ Author

Srinithi N  
Java Backend Developer
> 🚀 This project demonstrates my ability to design backend systems with real-world logic, data processing, and scalable architecture.
