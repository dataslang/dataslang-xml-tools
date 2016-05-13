package com.dataslang.xmlValidator.actions;

import com.dataslang.xmlValidator.util.ParseStream;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.common.PDMetadata;
import org.apache.pdfbox.preflight.PreflightDocument;
import org.apache.pdfbox.preflight.ValidationResult;
import org.apache.pdfbox.preflight.exception.SyntaxValidationException;
import org.apache.pdfbox.preflight.parser.PreflightParser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static com.dataslang.xmlValidator.util.ParseStream.getStringFromInputStream;

public class PdfEditor {
    private String pdf;
    private String output;
    private String xml;
    private boolean validate;

    public PdfEditor(String pdf, String output, String xml, boolean validate) {
        this.pdf = pdf;
        this.output = output;
        this.xml = xml;
        this.validate = validate;
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
        if(validate){
            typePDF();
        }else{
            if(getXml() == null)
                getMeta();
            else
                addMeta();
        }
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

    private void typePDF() throws IOException {
        ValidationResult result;
        String file = getPdf();

        try {
            PreflightParser parser = new PreflightParser(file);
            parser.parse();
            PreflightDocument document = parser.getPreflightDocument();
            document.validate();
            result = document.getResult();
            document.close();
        } catch (SyntaxValidationException e) {
            result = e.getResult();
        }

        if (result.isValid())
        {
            System.out.println("The file " + file + " is a valid PDF/A-1b file");
        }
        else
        {
            System.out.println("The file" + file + " is not valid, error(s) :");
            for (ValidationResult.ValidationError error : result.getErrorsList())
            {
                System.out.println(error.getErrorCode() + " : " + error.getDetails());
            }
            System.exit(1);
        }
    }
}
