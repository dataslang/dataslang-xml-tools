package com.dataslang.xmlValidator.actions;

import com.lowagie.text.DocumentException;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class HTMLUtilTest {

    String expectedPdf = "src/main/resources/html/expected/out.pdf";
    String html = "src/main/resources/html/test.html";
    String pdf = "src/main/resources/html/out.pdf";

    @Test
    public void testTransform() throws IOException, DocumentException {
        HTMLUtil htmlUtil = new HTMLUtil(html, pdf);
        htmlUtil.transform();
        File expectedFile = new File(expectedPdf);
        File gotFile = new File(pdf);
        assertEquals(expectedFile.length(), gotFile.length());
    }

    @Test (expected = FileNotFoundException.class)
    public void testFileNotFound() throws IOException, DocumentException {
        HTMLUtil htmlUtil = new HTMLUtil("src/mail/resources/html/est.html", pdf);
        htmlUtil.transform();
    }

    @Test (expected = NullPointerException.class)
    public void testNullHtml() throws IOException, DocumentException {
        HTMLUtil htmlUtil = new HTMLUtil(null, pdf);
        htmlUtil.transform();
    }

    @Test (expected = NullPointerException.class)
    public void testNullPdf() throws IOException, DocumentException {
        HTMLUtil htmlUtil = new HTMLUtil(html, null);
        htmlUtil.transform();
    }
}