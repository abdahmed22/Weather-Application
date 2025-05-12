# Weather Monitoring System

## Overview

This project implements a weather monitoring system where a **server** receives real-time weather updates from multiple sensors through **TCP connections**. Multiple **HTTP clients** can fetch live weather data from the server. The project includes a TCP client for the sensors, an HTTP client for users to request weather data, and a Spring Boot REST API server handling both HTTP requests and TCP connections.

The project aims to:
- Handle TCP connections for sensor data.
- Process and display live weather data through HTTP requests.
- Implement multithreading for handling concurrent sensor updates and client requests.
- Ensure thread safety when storing weather data.

---

## Features

### Sensor (Java TCP Client)
- **Console-based** client to enter weather data:
  - City name
  - Temperature
  - Humidity
- Sends the entered data and timestamp to the server.
- Receives an acknowledgment.

### Client (Java HTTP Client)
- **Console-based** client to request weather data:
  - Takes a city name as input.
  - Sends a **GET request** to fetch weather information.
  - Displays temperature and humidity on the console.
  - If no data is available for the city, the server returns a message "No data available".

### Server (Spring Boot REST API + TCP Server)
- **TCP Server**:
  - Listens for incoming TCP connections from sensors.
  - Handles each sensor connection in a separate thread (multithreading).
  - Receives and processes weather information.
  - Sends an acknowledgment to the sensor after receiving the data.
  - Stores the latest weather reading for each city (overwrites older data).
- **REST API**:
  - **GET Endpoint**:
    - Receives a city name.
    - Returns the latest weather information (city, temperature, humidity) in JSON format.
    - If no data is found, returns a "No data available" message in JSON format.

### Thread Safety
- Ensures that **weather data** is accessed and updated in a thread-safe manner to handle concurrent TCP and HTTP requests.

