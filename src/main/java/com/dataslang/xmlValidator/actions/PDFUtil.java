package com.dataslang.xmlValidator.actions;

import com.dataslang.xmlValidator.util.ParseStream;
import org.apache.jempbox.xmp.XMPMetadata;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.common.PDMetadata;
import org.apache.pdfbox.preflight.PreflightDocument;
import org.apache.pdfbox.preflight.ValidationResult;
import org.apache.pdfbox.preflight.exception.SyntaxValidationException;
import org.apache.pdfbox.preflight.parser.PreflightParser;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static com.dataslang.xmlValidator.util.ParseStream.getStringFromInputStream;

public class PDFUtil {
    private String pdf;
    private String xml;
    private String output;

    public PDFUtil(String pdf, String xml, String output) {
        this.pdf = pdf;
        this.xml = xml;
        this.output = output;
    }

    private String getPdf() {
        return pdf;
    }

    private String getXml() {
        return xml;
    }

    private String getOutput() {
        return output;
    }

    public void validatePDF() throws IOException {
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

    public void getMeta() throws IOException {

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

    public void setMeta() throws ParserConfigurationException, IOException, SAXException, TransformerException, COSVisitorException {
        DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
        f.setExpandEntityReferences(true);
        f.setIgnoringComments(true);
        f.setIgnoringElementContentWhitespace(true);
        f.setValidating(false);
        f.setCoalescing(true);
        f.setNamespaceAware(true);

        DocumentBuilder builder = f.newDocumentBuilder();
        Document xmpDoc = builder.parse(getXml());

        InputStream in = new FileInputStream(getPdf());
        PDFParser parser = new PDFParser(in);
        parser.parse();

        PDDocument document=  parser.getPDDocument();

        PDDocumentCatalog cat = document.getDocumentCatalog();
        PDMetadata metadata = new PDMetadata(document);
        metadata.importXMPMetadata((new XMPMetadata(xmpDoc)));
        cat.setMetadata(metadata);
        if(getOutput() == null){
            document.save(getPdf());
        }else{
            document.save(getOutput());
        }
    }

}
