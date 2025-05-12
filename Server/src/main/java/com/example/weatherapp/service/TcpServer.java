package com.example.weatherapp.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import org.springframework.stereotype.Service;

@Service
public class TcpServer {
    private final WeatherService weatherService;

    public TcpServer(WeatherService weatherService) {
        this.weatherService = weatherService;
        startTcpServer();
    }

    private void startTcpServer() {
        new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(5000)) {
                while (true) {
                    Socket clientSocket = serverSocket.accept();
                    new Thread(new SensorHandler(clientSocket, weatherService)).start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    static class SensorHandler implements Runnable {
        private final Socket socket;
        private final WeatherService weatherService;

        SensorHandler(Socket socket, WeatherService weatherService) {
            this.socket = socket;
            this.weatherService = weatherService;
        }

        @Override
        public void run() {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)) {

                String data;
                while ((data = reader.readLine()) != null) { 
                    String[] parts = data.split(",");
                    if (parts.length == 3) {
                        String city = parts[0];
                        double temperature = Double.parseDouble(parts[1]);
                        double humidity = Double.parseDouble(parts[2]);

                        weatherService.updateWeather(city, temperature, humidity);
                        writer.println("Data received successfully");
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}