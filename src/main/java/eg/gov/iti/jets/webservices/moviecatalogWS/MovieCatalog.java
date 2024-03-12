package eg.gov.iti.jets.webservices.moviecatalogWS;


import eg.gov.iti.jets.webservices.moviecatalogWS.exception.GenericException;
import eg.gov.iti.jets.webservices.moviecatalogWS.exception.InvalidNumberOfMoviesException;
import eg.gov.iti.jets.webservices.moviecatalogWS.exception.TitleNotFoundException;
import eg.gov.iti.jets.webservices.moviecatalogWS.model.Movie;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

import java.util.List;

@WebService
public interface MovieCatalog {

    @WebMethod
    String getMovieDetails(@WebParam(name = "title") String title) throws TitleNotFoundException, GenericException;

    @WebMethod
    List<Movie> getTopRatedMovies(int numberOfMovies) throws InvalidNumberOfMoviesException, GenericException;

}
