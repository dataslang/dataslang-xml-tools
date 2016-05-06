package xmlValidator;

import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;

public class Validator {
    private String xml;
    private String xsd;
    private boolean debug;

    public Validator(String xml, String xsd, boolean debug) {
        this.xml = xml;
        this.xsd = xsd;
        this.debug = debug;
    }

    private String getXml(){
        return this.xml;
    }

    private String getXsd(){
        return this.xsd;
    }

    private boolean isDebug(){
        return this.debug;
    }

    public void validate(){
        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = null;
        try {
            schema = factory.newSchema(new File(getXsd()));
        } catch (SAXException e) {
            if(isDebug())
                e.printStackTrace();
            else
                System.out.println(e.getMessage());
            System.exit(2);
        }
        javax.xml.validation.Validator validator = schema.newValidator();
        try {
            validator.validate(new StreamSource(new File(getXml())));
        } catch (IOException | SAXException e) {
            if(isDebug())
                e.printStackTrace();
            else
                System.out.println(e.getMessage());
            System.exit(3);
        }
    }
}
