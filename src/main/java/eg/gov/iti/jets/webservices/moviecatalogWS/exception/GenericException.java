package eg.gov.iti.jets.webservices.moviecatalogWS.exception;

import jakarta.xml.ws.WebFault;

@WebFault(faultBean = "eg.gov.iti.jets.exception.CustomExBean")
public class GenericException extends Exception{
    private static final String FAULT_CODE = "GENERIC_ERROR";

    public GenericException(String message) {
        super(message);
    }
    public String getFaultCode() {
        return FAULT_CODE;
    }
}
