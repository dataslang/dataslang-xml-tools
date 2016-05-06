package com.dataslang.xmlValidator;

public class PdfEditor {
    private String pdf;
    private String output;
    private String xml;
    private boolean debug;

    public PdfEditor(String pdf, String output, String xml, boolean debug) {
        this.pdf = pdf;
        this.output = output;
        this.xml = xml;
        this.debug = debug;
    }

    private boolean isDebug() {
        return debug;
    }

    private String getPdf() {
        return pdf;
    }

    private String getOutput() {
        return output;
    }

    private String getXml() {
        return xml;
    }

    public void getMeta(){
        //TODO: get metadata from file then print it
    }

    public void addMeta(){
        //TODO: add metadata to pdf
    }
}
