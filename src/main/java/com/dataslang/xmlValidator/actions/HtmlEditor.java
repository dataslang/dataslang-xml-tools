package com.dataslang.xmlValidator.actions;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.html.simpleparser.HTMLWorker;
import com.lowagie.text.pdf.PdfWriter;

import java.io.*;

public class HtmlEditor {
    private String html;
    private String output;

    public HtmlEditor(String html, String output) {
        this.html = html;
        this.output = output;
    }

    private String getHtml() {
        return html;
    }

    private String getOutput() {
        return output;
    }

    public void transform() throws IOException, DocumentException {
        Document document = new Document(PageSize.A4);
        FileOutputStream fos = new FileOutputStream(getOutput());
        PdfWriter pdfWriter = PdfWriter.getInstance(document, fos);
        document.open();

        HTMLWorker htmlWorker = new HTMLWorker(document);
        htmlWorker.parse(new BufferedReader(new FileReader(getHtml())));
    }
}
