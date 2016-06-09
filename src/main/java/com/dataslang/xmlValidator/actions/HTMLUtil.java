package com.dataslang.xmlValidator.actions;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.html.simpleparser.HTMLWorker;
import com.lowagie.text.pdf.PdfWriter;
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.xhtmlrenderer.resource.XMLResource;

import java.io.*;

public class HTMLUtil {
    private String html;
    private String output;
    private int stat;

    public HTMLUtil(String html, String output, int stat) {
        this.html = html;
        this.output = output;
        this.stat = stat;
    }

    private String getHtml() {
        return html;
    }

    private String getOutput() {
        return output;
    }

    private int getStat(){
        return stat;
    }

    public void transform() throws IOException, DocumentException, com.itextpdf.text.DocumentException {
        switch(getStat()){
            case(1):
                org.w3c.dom.Document document = XMLResource.load( new ByteArrayInputStream( getStringFromFile().getBytes() ) ).getDocument();

                ITextRenderer renderer = new ITextRenderer();
                renderer.setDocument( document, null );

                renderer.layout();

                FileOutputStream fos = new FileOutputStream( getOutput() );
                renderer.createPDF( fos );
                fos.close();
            break;
            case(2):
                ITextRenderer rendererTwo = new ITextRenderer();
                rendererTwo.setDocumentFromString( getStringFromFile() );
                rendererTwo.layout();

                FileOutputStream fosTwo = new FileOutputStream( getOutput() );
                rendererTwo.createPDF( fosTwo );
                fosTwo.close();
            break;
            case(3):
                com.itextpdf.text.Document documentThree = new com.itextpdf.text.Document( com.itextpdf.text.PageSize.A4 );
                FileOutputStream fosThree = new FileOutputStream( getOutput() );
                com.itextpdf.text.pdf.PdfWriter pdfWriter = com.itextpdf.text.pdf.PdfWriter.getInstance( documentThree, fosThree );

                documentThree.open();

                documentThree.addAuthor( "Ravinder" );
                documentThree.addSubject( "HtmlWoker Parsed Pdf from iText" );
                documentThree.addCreationDate();
                documentThree.addTitle( "HtmlWoker Parsed Pdf from iText" );

                com.itextpdf.text.html.simpleparser.HTMLWorker htmlWorker = new com.itextpdf.text.html.simpleparser.HTMLWorker( documentThree );
                htmlWorker.parse( new StringReader( getStringFromFile() ) );

                documentThree.close();
                fosThree.close();
            break;
            case(4):
                Document documentFour = new Document(PageSize.A4);
                OutputStream fosfour= new FileOutputStream(getOutput());
                PdfWriter pdfWriterTwo = PdfWriter.getInstance(documentFour, fosfour);
                documentFour.open();

                HTMLWorker htmlWorkerTwo = new HTMLWorker(documentFour);
                htmlWorkerTwo.parse(new BufferedReader(new FileReader(getHtml())));

                documentFour.close();
                fosfour.close();
            break;
        }
    }

    private String getStringFromFile(){
        StringBuilder contentBuilder = new StringBuilder();
        try {
            BufferedReader in = new BufferedReader(new FileReader(getHtml()));
            String str;
            while ((str = in.readLine()) != null) {
                contentBuilder.append(str);
            }
            in.close();
        } catch (IOException e) {
        }
        return contentBuilder.toString();
    }
}
