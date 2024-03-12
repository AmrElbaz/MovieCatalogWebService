package eg.gov.iti.jets.webservices.moviecatalogWS.exception;

import jakarta.xml.ws.WebFault;

@WebFault(faultBean = "eg.gov.iti.jets.exception.CustomExBean")
public class TitleNotFoundException extends Exception{
    private static final String FAULT_CODE = "TITLE_NOT_FOUND";

    public TitleNotFoundException(String message) {
        super(message);
    }
    public String getFaultCode() {
        return FAULT_CODE;
    }
}
