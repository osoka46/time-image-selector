# 🕒 Time-Based Image Selector

## Project Description
This is a Java backend utility designed to simulate a dynamic background selector. The application accepts a time string as input and returns the corresponding image filename based on specific time ranges.

**the primary goal of this repository is to showcase a custom-built, distributed CI/CD infrastructure.**

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

---

## 🏗️ Infrastructure & DevOps Architecture

This project does not run on a simple local setup. Instead, it simulates a **Real-World Enterprise Environment** using a **Distributed Master-Slave Architecture** within a private Docker network.

### The "3-System" Simulation
To replicate a production-grade CI/CD pipeline, the environment is split into three distinct systems:

| System | Role | Technology | Responsibility |
| **System 1** | **Local Machine** | Host | Code development, Git operations, and local orchestration. |
| **System 2** | **Orchestrator** | Jenkins Controller (Docker) | Manages the pipeline, schedules jobs, and monitors the agent. |
| **System 3** | **Execution Node** | Jenkins Agent (Docker) | A dedicated Linux-based worker that executes tests and builds artifacts in isolation. |

### Technical Implementation Details
* **Docker-in-Docker (DinD):** The Jenkins Controller communicates with the Docker Daemon to manage resources dynamically.
* **SSH Handshake:** Secure communication is established between the Controller and the Agent using generated SSH keys.
* **Volume Persistence:** Data and workspace persistence are handled via Docker Volumes.

---

## 🚀 CI/CD Pipeline Workflow

The automation pipeline (`Jenkinsfile`) follows a strict **Build -> Test -> Report -> Notify** flow:

1.  **Trigger:**
    - Smoke test (every day, between 03:00-04:00)
    - Regression test (biweekly, between 03:00-04:00)
3.  **Delegate:** The Controller assigns the job to the **"System 3" (Agent)** node.
4.  **Execution:**
    * `mvn clean test` is executed inside the isolated container.
    * Unit tests (JUnit 5) verify the logic.
5.  **Reporting:** Test results are archived, and a JUnit report is generated.
6.  **Notification:** An automated email is sent to the developer with the build status.

---

## Technologies Used
* **Language:** Java 17
* **Build Tool:** Maven
* **Testing:** JUnit 5
* **CI/CD:** Jenkins (Pipeline as Code)
* **Containerization:** Docker
* **Version Control:** Git & GitHub

## How to Run Tests Locally
To run the logic tests without starting the full infrastructure:
mvn clean test
