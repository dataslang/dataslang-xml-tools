package com.dataslang.xmlValidator.actions;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayOutputStream;
import java.io.File;

public class Transformator {
    private String xml;
    private String xslt;
    private String output;

    public Transformator(String xml, String xslt, String output) {
        this.xml = xml;
        this.xslt = xslt;
        this.output = output;
    }

    private String getXml() {
        return xml;
    }

    private String getXslt() {
        return xslt;
    }

    private String getOutput() {
        return output;
    }

    public void transformate() throws TransformerException{
        TransformerFactory factory = TransformerFactory.newInstance();
        StreamSource xslStream = new StreamSource(this.getXslt());
        Transformer transformer = factory.newTransformer(xslStream);

        StreamSource in = new StreamSource(this.getXml());

        if(getOutput() == null){
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            transformer.transform(in, new StreamResult(outputStream));
            System.out.println(outputStream.toString());
        }else{
            Result xmlOutput = new StreamResult(new File(output));
            transformer.transform(in, xmlOutput);
        }

    }
}
