import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class sensorClient {
    public static void main(String[] args) {
        Socket socket = null;
        PrintWriter writer = null;
        BufferedReader reader = null;
        Scanner scanner = null;

        try {
            socket = new Socket("localhost", 5000);
            writer = new PrintWriter(socket.getOutputStream(), true);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            scanner = new Scanner(System.in);

            while (true) {
                System.out.print("Enter city: ");
                String city = scanner.nextLine();

                System.out.print("Enter temperature: ");
                double temperature = scanner.nextDouble();

                System.out.print("Enter humidity: ");
                double humidity = scanner.nextDouble();
                scanner.nextLine(); 

                String data = city + "," + temperature + "," + humidity;
                writer.println(data);

                String response = reader.readLine();
                System.out.println(response);

                System.out.print("Do you want to send another reading? (y/n): ");
                String choice = scanner.nextLine();
                
                if (!choice.equalsIgnoreCase("y")) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) 
                    writer.close();
                if (reader != null) 
                    reader.close();
                if (scanner != null) 
                    scanner.close();
                if (socket != null) 
                    socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
