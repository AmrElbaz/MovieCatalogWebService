package eg.gov.iti.jets.webservices.moviecatalogWS.handler;

import jakarta.xml.soap.SOAPException;
import jakarta.xml.soap.SOAPFault;
import jakarta.xml.ws.handler.MessageContext;
import jakarta.xml.ws.handler.soap.SOAPHandler;
import jakarta.xml.ws.handler.soap.SOAPMessageContext;

import javax.xml.namespace.QName;;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomExceptionHandler implements SOAPHandler<SOAPMessageContext> {

    private static final Logger logger = Logger.getLogger(CustomExceptionHandler.class.getName());


    @Override
    public Set<QName> getHeaders() {
        return null;
    }

    @Override
    public boolean handleMessage(SOAPMessageContext context) {
        Boolean isResponse = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);

        if (!isResponse) {  // Check if it's an incoming message (request)
            try {
                SOAPFault fault = context.getMessage().getSOAPBody().getFault();
                if (fault != null) {
                    String faultCode = fault.getFaultCode();
                    String faultString = fault.getFaultString();

                    // Extract fault details from the detail element
                    String faultDetails = getFaultDetails(fault);

                    logger.log(Level.INFO, "Fault Code: " + faultCode);
                    logger.log(Level.INFO, "Fault String: " + faultString);
                    logger.log(Level.INFO, "Fault Details: " + faultDetails);
                    System.out.println("TEST");


                }
            } catch (SOAPException e) {
                e.printStackTrace();
            }
        }

        return true; // Continue processing the message
    }

    private String getFaultDetails(SOAPFault fault) throws SOAPException {
        // Extract fault details from the detail element
        String faultDetails = null;
        if (fault.getDetail() != null) {
            faultDetails = fault.getDetail().getTextContent();
        }
        return faultDetails;
    }

    @Override
    public boolean handleFault(SOAPMessageContext context) {
        // This method is invoked when a fault is detected during the processing of a response
        return true; // Continue processing the message
    }

    @Override
    public void close(MessageContext context) {
        // Clean-up code, if needed
    }

}
