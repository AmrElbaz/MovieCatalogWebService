package eg.gov.iti.jets.webservices.moviecatalogWS;

import eg.gov.iti.jets.webservices.moviecatalogWS.exception.GenericException;
import eg.gov.iti.jets.webservices.moviecatalogWS.exception.InvalidNumberOfMoviesException;
import eg.gov.iti.jets.webservices.moviecatalogWS.exception.TitleNotFoundException;
import eg.gov.iti.jets.webservices.moviecatalogWS.MovieCatalog;
//import eg.gov.iti.jets.webservices.moviecatalogWS.handler.TopRatedMoviesHandler;
import eg.gov.iti.jets.webservices.moviecatalogWS.handler.TopRatedMoviesHandler;
import eg.gov.iti.jets.webservices.moviecatalogWS.model.Movie;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


import javax.xml.parsers.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

@WebService(endpointInterface = "eg.gov.iti.jets.webservices.moviecatalogWS.MovieCatalog")
public class MovieCatalogImpl implements MovieCatalog {
    private static final String MOVIE_DETAILS_XML_FILE = "/movie_details.xml";
    private static final String TOP_RATED_MOVIES_XML_FILE = "/top_rated_movies.xml";
    private static final int MAX_ALLOWED_MOVIES = 20;

    @Override
    public String getMovieDetails(@WebParam(name = "title") String title) throws TitleNotFoundException, GenericException {
        // Retrieve movie details from XML using DOM (Simplified for illustration)
        String movie = retrieveMovieDetailsFromXMLUsingDOM(title);
        if(movie == null || movie.trim().length() <= 0) {
            throw new TitleNotFoundException("Movie details not found for: " + title);
        }

        return movie;
    }

    @Override
    public List<Movie> getTopRatedMovies(int numberOfMovies) throws InvalidNumberOfMoviesException, GenericException {
        if (numberOfMovies <= 0 || numberOfMovies > MAX_ALLOWED_MOVIES) {
            throw new InvalidNumberOfMoviesException(String.format("Number of movies must be greater than zero and less than %s", MAX_ALLOWED_MOVIES));
        }

        return retrieveTopRatedMoviesFromXMLUsingSAX(numberOfMovies);
//        return null;
    }


    // Helper method to retrieve movie details from XML using DOM
    private String retrieveMovieDetailsFromXMLUsingDOM(String title) throws GenericException {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            File moviesDetails = new File(getClass().getResource(MOVIE_DETAILS_XML_FILE).toURI());
            Document document = builder.parse(moviesDetails);

            // Find the movie element with the specified title
            NodeList movieNodes = document.getElementsByTagName("movie");
            for (int i = 0; i < movieNodes.getLength(); i++) {
                Element movieElement = (Element) movieNodes.item(i);
                String currentTitle = movieElement.getElementsByTagName("title").item(0).getTextContent();

                if (title.equals(currentTitle)) {
                    // Extract details for the matching movie
                    return movieElement.getElementsByTagName("details").item(0).getTextContent();
                }
            }

            // Movie not found
            return null;
        } catch (ParserConfigurationException | org.xml.sax.SAXException | java.io.IOException| URISyntaxException e) {
            e.printStackTrace();
            throw new GenericException("Error retrieving movie details.");
        }
    }

    private List<Movie> retrieveTopRatedMoviesFromXMLUsingSAX(int numberOfMovies) throws InvalidNumberOfMoviesException, GenericException {
        if (numberOfMovies <= 0 || numberOfMovies > MAX_ALLOWED_MOVIES) {
            throw new InvalidNumberOfMoviesException(String.format("Number of movies must be greater than zero and less than %s", MAX_ALLOWED_MOVIES));
        }
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            TopRatedMoviesHandler handler = new TopRatedMoviesHandler(numberOfMovies);
            File topRatedMovies = new File(getClass().getResource(TOP_RATED_MOVIES_XML_FILE).toURI());

            saxParser.parse(topRatedMovies, handler);

            return handler.getTopRatedMovies();

        } catch (ParserConfigurationException | SAXException | IOException | URISyntaxException e) {
            e.printStackTrace();
            throw new GenericException("Error retrieving top rated movies.");

        }
    }

}
