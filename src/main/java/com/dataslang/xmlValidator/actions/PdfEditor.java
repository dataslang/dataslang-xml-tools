package com.dataslang.xmlValidator.actions;

import com.dataslang.xmlValidator.util.ParseStream;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.common.PDMetadata;

import java.io.*;

import static com.dataslang.xmlValidator.util.ParseStream.getStringFromInputStream;

public class PdfEditor {
    private String pdf;
    private String output;
    private String xml;

    public PdfEditor(String pdf, String output, String xml) {
        this.pdf = pdf;
        this.output = output;
        this.xml = xml;
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

    public void meta() throws IOException {
        if(getXml() == null)
            getMeta();
        else
            addMeta();
    }

    private void getMeta() throws IOException {

        PDDocument document = PDDocument.load(new File(getPdf()));

        PDDocumentCatalog catalog = document.getDocumentCatalog();
        PDMetadata metadata = catalog.getMetadata();

        InputStream xmlInputStream = metadata.createInputStream();

        if(getOutput() == null){
            System.out.println(getStringFromInputStream(xmlInputStream));
        }else{
            ParseStream.inputStreamSaveFile(xmlInputStream, getOutput());
        }

    }

    private void addMeta() throws IOException{
        InputStream inputStream = new FileInputStream(getXml());
        PDDocument document = PDDocument.load(new File(getPdf()));
        PDDocumentCatalog catalog = document.getDocumentCatalog();

        PDMetadata newMetadata = new PDMetadata(document, inputStream);
        catalog.setMetadata(newMetadata);

    }

}
