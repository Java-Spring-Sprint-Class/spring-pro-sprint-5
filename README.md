# Homework: Spring Boot Exception Handling

## Task Description
Currently, your API returns the default (and ugly) "Whitelabel Error Page" or an unclear stack trace when an error occurs.  
Your goal is to **intercept exceptions centrally** and return a clean, understandable JSON response to the client with the correct HTTP status code.

You will use Spring’s **AOP (Aspect Oriented Programming) mechanism**: `@RestControllerAdvice`.

## Goals
- Create a **JSON response structure** for errors (DTO).
- Implement a **global exception handler** (`GlobalExceptionHandler`).
- Map Java exceptions to HTTP status codes:
    - `ProductNotFoundException` → 404 Not Found
    - `IllegalArgumentException` → 400 Bad Request
    - Any other exception → 500 Internal Server Error

## Technical Requirements

### 1. Error Response DTO
Create a class `ErrorResponse` that will be sent to the client in case of an error. It should contain:
- `int status` (error code, e.g., 404)
- `String message` (error message)
- `LocalDateTime timestamp` (time of the error)

### 2. Global Exception Handler
- In the `exception` package, find or create a class `GlobalExceptionHandler`.
- Annotate it with `@RestControllerAdvice`.
- Create methods annotated with `@ExceptionHandler`.

### 3. Scenarios to Handle
The `ProductController` is already implemented and simulates different errors:

1. **ProductNotFoundException**: When thrown (product ID not found), the API should return JSON with status 404.
2. **IllegalArgumentException**: When thrown (e.g., price < 0), the API should return JSON with status 400.
3. **Any other unexpected error (`Exception.class`)**: The API should return `"General Error"` with status 500.

## How to Verify
1. Run the application.
2. Use Postman or a browser:
    - `GET /products/1` → Success (200 OK)
    - `GET /products/99` → Returns JSON + 404
    - `POST /products/create` (with parameter `price=-100`) → Returns JSON + 400
3. Run `mvn test` to ensure the autograder accepts your solution.
