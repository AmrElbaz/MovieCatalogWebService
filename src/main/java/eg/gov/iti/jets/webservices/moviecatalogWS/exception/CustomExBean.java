package eg.gov.iti.jets.webservices.moviecatalogWS.exception;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CustomExBean", propOrder = {
        "faultCode",
        "message"
})
public class CustomExBean {

    protected String faultCode;
    protected String message;

    public String getFaultCode() {
        return faultCode;
    }

    public void setFaultCode(String value) {
        this.faultCode = value;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String value) {
        this.message = value;
    }
}

