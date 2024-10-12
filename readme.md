# Customer Feedback Application

This project is a Customer Feedback Application built using Java, Spring Boot, Maven, TypeScript, JavaScript, npm, and React.

## Getting Started

To get a local copy up and running, follow these steps.

### Prerequisites

- Java 21 or higher
- Maven
- Postgres
- Node.js and npm

## Backend Setup

1. Build the project using Maven:
    ```sh
    mvn clean install
    ```

2. Inside `docker-frontend` directory
   ```sh
    docker compose up -d
    ```

3. Run the Spring Boot application:
    ```sh
    mvn spring-boot:run
    ```

## Frontend Setup

1. Navigate to the frontend directory:
    ```sh
    cd feedback-frontend
    ```

2. Install npm packages:
    ```sh
    npm install
    ```

3. Start the React application:
    ```sh
    npm start
    ```

## Usage

Once both the backend and frontend are running, you can access the application at `http://localhost:3000`.
