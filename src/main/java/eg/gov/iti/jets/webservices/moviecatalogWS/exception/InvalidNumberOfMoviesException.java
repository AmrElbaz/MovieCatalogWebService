package eg.gov.iti.jets.webservices.moviecatalogWS.exception;

import jakarta.xml.ws.WebFault;

@WebFault(faultBean = "eg.gov.iti.jets.exception.CustomExBean")
public class InvalidNumberOfMoviesException extends Exception {

    private static final String FAULT_CODE = "INVALID_NUMBER";

    public InvalidNumberOfMoviesException(String message) {
//        super("Invalid number of movies");
        super(message);
    }

    public String getFaultCode() {
        return FAULT_CODE;
    }
}
