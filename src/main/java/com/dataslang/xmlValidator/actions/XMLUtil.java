package com.dataslang.xmlValidator.actions;

import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class XMLUtil {
    private String firstFile;
    private String secondFile;
    private String output;

    public XMLUtil(String firstFile, String secondFile) {
        this.firstFile = firstFile;
        this.secondFile = secondFile;
        this.output = null;
    }

    public XMLUtil(String firstFile, String secondFile, String output) {
        this.firstFile = firstFile;
        this.secondFile = secondFile;
        this.output = output;
    }

    private String getFirstFile() {
        return firstFile;
    }

    private String getSecondFile() {
        return secondFile;
    }

    private String getOutput() {
        return output;
    }

    public void validate() throws SAXException, IOException {

        File xml = new File(getFirstFile());
        File xsd = new File(getSecondFile());

        if(!xsd.exists())
            throw new FileNotFoundException(xsd.getAbsolutePath() + " (No such file or directory)");
        if(!xml.exists())
            throw new FileNotFoundException(xml.getAbsolutePath() + " (No such file or directory)");

        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = factory.newSchema(xsd);

        javax.xml.validation.Validator validator = schema.newValidator();
        validator.validate(new StreamSource(xml));
        System.out.println("File Valido");

    }

    public void transformate() throws TransformerException {
        TransformerFactory factory = TransformerFactory.newInstance();
        StreamSource xslStream = new StreamSource(this.getSecondFile());
        Transformer transformer = factory.newTransformer(xslStream);

        StreamSource in = new StreamSource(this.getFirstFile());

        if(getOutput() == null){
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            transformer.transform(in, new StreamResult(outputStream));
            System.out.println(outputStream.toString());
        }else{
            Result xmlOutput = new StreamResult(new File(output));
            transformer.transform(in, xmlOutput);
            System.out.println("Trasformazione riuscita");
        }

    }

}
