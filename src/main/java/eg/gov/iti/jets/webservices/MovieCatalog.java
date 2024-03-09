package eg.gov.iti.jets.webservices;


import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

@WebService
public interface MovieCatalog {

    @WebMethod
    String getMovieDetails(@WebParam(name = "title") String title);

}
