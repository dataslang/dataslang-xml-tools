package com.dataslang.xmlValidator.actions;

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

    public Validator(String xml, String xsd) {
        this.xml = xml;
        this.xsd = xsd;
    }

    private String getXml(){
        return this.xml;
    }

    private String getXsd(){
        return this.xsd;
    }

    public void validate() throws SAXException, IOException{
        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = factory.newSchema(new File(getXsd()));

        javax.xml.validation.Validator validator = schema.newValidator();
        validator.validate(new StreamSource(new File(getXml())));
    }
}
