package eg.gov.iti.jets.webservices.impl;

import eg.gov.iti.jets.webservices.MovieCatalog;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;

@WebService(endpointInterface = "eg.gov.iti.jets.webservices.MovieCatalog")
public class MovieCatalogImpl implements MovieCatalog {
    private static final String MOVIE_DETAILS_XML_FILE = "movie_details.xml";

    @Override
    public String getMovieDetails(@WebParam(name = "title") String title) {
        // Retrieve movie details from XML using DOM (Simplified for illustration)
        return retrieveMovieDetailsFromXMLUsingDOM(title);
    }


    // Helper method to retrieve movie details from XML using DOM
    private String retrieveMovieDetailsFromXMLUsingDOM(String title) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(MOVIE_DETAILS_XML_FILE));

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
            return "Movie details not found for: " + title;

        } catch (ParserConfigurationException | org.xml.sax.SAXException | java.io.IOException e) {
            e.printStackTrace();
            return "Error retrieving movie details.";
        }
    }
}
