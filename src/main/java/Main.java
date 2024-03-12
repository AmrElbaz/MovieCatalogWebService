import eg.gov.iti.jets.webservices.moviecatalogWS.MovieCatalogImpl;
import jakarta.xml.ws.Endpoint;

public class Main {
    public static void main(String[] args) {
        // Specify the address where the web service should be published
        String address = "http://localhost:8081/MovieCatalogWebService";

        // Create an instance of your web service implementation class
        MovieCatalogImpl movieCatalog = new MovieCatalogImpl();

        // Publish the web service at the specified address
        Endpoint.publish(address, movieCatalog);

        // Display a message indicating that the web service has been published
        System.out.println("MovieCatalogWebService published at: " + address);

    }
}
