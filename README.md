# TaskFlow Frontend

TaskFlow is a task management application designed to help individuals efficiently organize their personal tasks and boost productivity. This repository contains the Backend implementation of TaskFlow, built using Java, Spring Boot and Spring Security. 


## Table of Contents
1. [Overview](#overview)
2. [Features](#features)
2. [Technologies Used](#tech-stack)
3. [Project Structure](#project-structure)
4. [Project Setup](#Project Setup)
5. [Usage](#usage)
6. [API Documentation](#api-documentation)
7. [Contributing](#contributing)
8.

## Overview

### Background
Individuals require an effective solution to efficiently organize their personal tasks spanning various related and/or unrelated responsibilities, aiming to boost productivity.

### Target Users
Individuals seeking personal task organization.

### User Story
As a user, I want to be able to sign up for an account so that I can access the application and manage my tasks.

#### Acceptance Criteria
- As a new user, I can sign up for a new account.
    - On the sign-up form, I can provide my first name, last name, email, password, confirm password, and phone number.
    - Upon successful sign-up, I should receive a confirmation email with a token to validate my account, after which I can log in using my email and password.
- As a registered user, I can log in to my account.
    - On login, I will use my email and password.
    - Upon successful login, I should be able to:
        1. Create tasks with specific details such as title, description, deadline, and priority level (HIGH, MEDIUM, LOW, NONE).
        2. View details of a specific task by its title or ID.
        3. View all tasks associated with a specific user.
        4. Edit a task successfully.
        5. Delete a task successfully.
        6. Filter tasks by their status (PENDING, IN_PROGRESS, COMPLETED).
        7. View a list of completed tasks separately.
        8. Log out of my account.

## Technologies Used

- **Frontend:**
    - HTML
    - CSS
    - JavaScript
    - Bootstrap

- **Backend:**
    - Spring Boot
    - Spring Security / JWT
    - MySQL
    - Postman
    - GIT

## Project Setup and Installation

### Prerequisites

### Frontend

**You can use Postman (for testing the API endpoints):**
1. Download and install Postman from here.
2. Use Postman to send requests to the API endpoints in the Controllers and verify their responses.

**Or Clone the Frontend Repo (for a simple frontend setup):**
1. Clone the frontend repository from here.
    ``` sh
        git clone https://github.com/Tiana-Coker/week10-frontend.git
        cd week10-frontend 
   ```

2. Open the project in Visual Studio Code (VS Code).
    a. VS Code (recommended for Live Server):
    b. Download and install Visual Studio Code from here.
    c. Install the Live Server extension in VS Code.
3. After cloning, open the index.html file in VS Code and run it with Live Server to view the Landing page.

This setup will allow you to test the TaskFlow backend API endpoints either using Postman or by setting up a simple frontend with Live Server. Ensure the backend server is running and accessible at http://localhost:8080 before proceeding with these options.


### Backend
1. Clone the repository:
    ```bash
    git clone https://github.com/yourusername/backend-repo.git
    cd backend-repo
    ```

2. Configure the MySQL database:
    - Update the `application.properties` file with your MySQL credentials.

3. Build and run the application:

   **Using the Maven wrapper:**
   Run the following commands:
    ```bash
   ./mvnw clean install
   ./mvnw spring-boot:run
   ```

   **Using IntelliJ IDEA:**
   Open the project in IntelliJ IDEA.
   Click the play button (Run) in IntelliJ to start the application.


## Usage
1. Ensure both frontend and backend servers are running.
2. Open the frontend application in your browser.
3. Register or log in to your account.
4. Create, view, edit, or delete your tasks.

## Usage

### Screenshots

### Features

- **User Authentication:**
    - Sign up, login, and receive email confirmation for new accounts.

- **Task Management:**
    - Create, view, edit, delete, and filter tasks.
    - View tasks by status and see a list of completed tasks.

- **User Interface:**
    - Clean and responsive design using Bootstrap.

## Contributing

We welcome contributions from the community. To contribute, please follow these steps:

1. Fork the repository.
2. Create a new branch:
    ```sh
    git checkout -b feature/your-feature-name
    ```
3. Make your changes and commit them:
    ```sh
    git commit -m "Add your feature"
    ```
4. Push to the branch:
    ```sh
    git push origin feature/your-feature-name
    ```
5. Open a pull request to the main repository.

## License

This project is licensed under the MIT License.

## Contact

For any questions or suggestions, feel free to reach out or open an issue on GitHub.

---

_Thank you for using TaskFlow!_
