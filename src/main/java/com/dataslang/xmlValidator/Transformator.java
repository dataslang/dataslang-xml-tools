package com.dataslang.xmlValidator;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayOutputStream;
import java.io.File;

public class Transformator {
    private String xml;
    private String xslt;
    private String output;
    private boolean debug;

    public Transformator(String xml, String xslt, String output, boolean debug) {
        this.xml = xml;
        this.xslt = xslt;
        this.output = output;
        this.debug = debug;
    }

    public Transformator(String xml, String xslt, boolean debug) {
        this.xml = xml;
        this.xslt = xslt;
        this.debug = debug;
    }

    private String getXml() {
        return xml;
    }

    private String getXslt() {
        return xslt;
    }

    private boolean isDebug() {
        return debug;
    }

    private String getOutput() {
        return output;
    }

    public void transformate(){
        TransformerFactory factory = TransformerFactory.newInstance();
        StreamSource xslStream = new StreamSource(this.getXslt());
        Transformer transformer = null;

        try {
             transformer = factory.newTransformer(xslStream);
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        }

        StreamSource in = new StreamSource(this.getXml());

        if(output == null){
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            try {
                transformer.transform(in, new StreamResult(outputStream));
            } catch (TransformerException e) {
                e.printStackTrace();
            }
            System.out.println(outputStream.toString());
        }else{
            try{
                Result xmlOutput = new StreamResult(new File(output));
                transformer.transform(in, xmlOutput);
            } catch (TransformerException e) {
                e.printStackTrace();
            }
        }

    }
}
