package eg.gov.iti.jets.webservices;


import eg.gov.iti.jets.model.Movie;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

import java.util.List;

@WebService
public interface MovieCatalog {

    @WebMethod
    String getMovieDetails(@WebParam(name = "title") String title);

    @WebMethod
    List<Movie> getTopRatedMovies(int numberOfMovies);

}
