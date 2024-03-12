package eg.gov.iti.jets.webservices.moviecatalogWS.handler;

import eg.gov.iti.jets.webservices.moviecatalogWS.model.Movie;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.*;

public class TopRatedMoviesHandler extends DefaultHandler {
    private List<Movie> topRatedMovies = new ArrayList<>();
    private Movie currentMovie;
    private boolean inTitleElement = false;
    private int numberOfMovies;
    private int moviesCount;

    public TopRatedMoviesHandler(int numberOfMovies) {
        this.numberOfMovies = numberOfMovies;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if ("movie".equals(qName)) {
            if (moviesCount < numberOfMovies) {
                currentMovie = new Movie("");
            }
        } else if ("title".equals(qName)) {
            inTitleElement = true;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (inTitleElement && currentMovie != null) {
            String movieTitle = new String(ch, start, length).trim();
            currentMovie.setTitle(movieTitle);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if ("title".equals(qName)) {
            inTitleElement = false;
        } else if ("movie".equals(qName) && currentMovie != null) {
            topRatedMovies.add(currentMovie);
            currentMovie = null;  // Reset currentMovie for the next iteration
            moviesCount++;
        }
    }

    public List<Movie> getTopRatedMovies() {
        return topRatedMovies;
    }
}
