# Library Management System

A Java-based Library Management System to manage library operations such as issuing and returning books, managing students, books, tables, and admin functionalities.

---

## Technologies Used

* Java
* Maven (Build & Dependency Management)
* Hibernate & JPA (ORM for database interaction)
* MySQL (Relational Database)
* Postman (API testing)

---

## Features

* Manage book records (create, update, search)
* Manage student records
* Issue and return books through transactions
* Admin management
* Future enhancements planned: Spring Security, validation, Swagger docs, deployment setup

---

## Setup & Running the Application

1. **Clone the repository**

   ```bash
   git clone https://github.com/VaishnaviAgrawa12/Library-Management-System.git
   ```

2. **Configure Environment**

   Update your MySQL credentials and settings in `application.properties` or `application.yml`.

3. **Build the project**

   ```bash
   mvn clean install
   ```

4. **Run the application**

   ```bash
   mvn spring-boot:run
   ```

5. **Test APIs**

   Use Postman to interact with the API endpoints listed below.

---

## API Endpoints

### Admin

| Method | Endpoint | Description             |
| ------ | -------- | ----------------------- |
| POST   | `/admin` | Create a new admin user |

### Book

| Method | Endpoint | Description                    |
| ------ | -------- | ------------------------------ |
| POST   | `/book`  | Create or update a book record |
| GET    | `/book`  | Search books by key and value  |

### Student

| Method | Endpoint   | Description                            |
| ------ | ---------- | -------------------------------------- |
| POST   | `/student` | Create a new student                   |
| GET    | `/student` | Find student by ID (query param: `id`) |

### Transaction

| Method | Endpoint               | Description                                                                             |
| ------ | ---------------------- | --------------------------------------------------------------------------------------- |
| POST   | `/transaction`         | Initiate a transaction (issue/return)                                                   |
| POST   | `/transaction/payment` | Make a payment for a transaction (query params: `amount`, `studentId`, `transactionId`) |

---

## Future Enhancements

* Add **Spring Security** for authentication and authorization
* Improved validation and error handling
* Swagger API documentation
* Deployment and CI/CD pipeline integration

---

## Contribution

Feel free to fork, raise issues, and submit pull requests to improve this project.

---

## License

This project is open source under the MIT License.


