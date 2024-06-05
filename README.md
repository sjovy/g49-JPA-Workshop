# Project Title: Library Management System

This project is a backend application for managing a library. It is built using Java, Spring Boot, and Maven.

## Features

- **Book Management:** Allows the addition, removal, and modification of books. Each book has an ISBN, title, maximum loan days, and availability status.
- **Author Management:** Allows the addition, removal, and modification of authors. Each author has a first name and last name.
- **Book Loan Management:** Manages the loaning of books to users. Each loan has a loan date, due date, and return status.
- **User Management:** Manages user details including name, email, and birthday.

## Entities

- **Book:** Represents a book in the library. It has relationships with `Author` and `BookLoan`.
- **Author:** Represents an author of a book. It has a relationship with `Book`.
- **BookLoan:** Represents a loan of a book to a user. It has relationships with `Book` and `AppUser`.
- **AppUser:** Represents a user of the application. It has a relationship with `BookLoan`.
- **Details:** Represents the details of a user. It includes name, email, and birthday.

## Technologies Used

- **Java:** The main programming language used in this project.
- **Spring Boot:** Used to create stand-alone, production-grade Spring based applications.
- **Maven:** Used for building and managing the project.

## Setup

To run this project, you need to have Java and Maven installed on your machine. Clone the repository and navigate to the project directory. Run the project using the following command:

```bash
mvn spring-boot:run
```

## Future Enhancements

- Integration with a frontend application built using React or Angular.
- Addition of more features like book reservations, user notifications, etc.
