import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class httpClient {
    public static void main(String[] args) {
        HttpClient httpClient = HttpClient.newHttpClient();
        Scanner scanner = null;

        try {
            scanner = new Scanner(System.in);

            while (true) {
                System.out.print("Enter city name: ");
                String city = scanner.nextLine().trim();

                if (city.isEmpty()) {
                    continue;
                }

                try {
                    HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/weather/" + city)).GET().build();

                    HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

                    System.out.println(response.body());

                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.print("Do you want to check another city? (y/n): ");
                String choice = scanner.nextLine();
                
                if (!choice.equalsIgnoreCase("y")) {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }
}
