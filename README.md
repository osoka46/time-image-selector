# Time-Based Image Selector

## Project Description
This is a Java backend utility designed to simulate a dynamic background selector. 
The application accepts a time string as input and returns the corresponding image filename based on specific time ranges.

This project is of the goal demonstrating the integration of coding logic with modern CI/CD infrastructure.

## Business Logic
The application determines the output based on the following rules:

| Time Range | Output Image |
| :--- | :--- |
| **07:00 - 11:59** | `morning.jpg` |
| **12:00 - 13:59** | `noon.jpg` |
| **14:00 - 17:59** | `afternoon.jpg` |
| **18:00 - 21:59** | `evening.jpg` |
| **22:00 - 06:59** | `night.jpg` |
| **Invalid Input** | `broken_watch.jpg` |

## Technologies & Infrastructure
* **Language:** Java 17
* **Build Tool:** Maven
* **Testing:** JUnit 5
* **CI/CD:** Jenkins
* **Containerization:** Docker (Target Runtime)
* **Version Control:** Git & GitHub

## Architecture Goal
The goal of this project is to simulate a real-world automated testing environment where:
1.  Code is pushed to **GitHub**.
2.  **Jenkins** triggers the pipeline.
3.  Tests are executed in a **Dockerized** environment.
4.  Artifacts are deployed to a container registry.

## How to Run Tests Locally
```bash
mvn clean test