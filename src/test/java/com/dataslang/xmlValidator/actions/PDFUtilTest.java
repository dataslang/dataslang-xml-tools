package com.dataslang.xmlValidator.actions;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class PDFUtilTest {

    String pdf = "src/main/resources/pdf/MustangGnuaccountingBeispielRE-20151008_504blanko.pdf";
    String xmlTargetMustang = "src/main/resources/pdf/outMustangBlank.xml";
    String xmlExpectedMustang = "src/main/resources/pdf/expected/outMustangBlank.xml";

    String pdfTargetSet = "src/main/resources/pdf/zugferd_example_invoice_en.pdf";
    String xmlToSet = "src/main/resources/xml/output.xml";
    String pdfExpectedSet = "src/main/resources/pdf/expected/setMetaOutput.xml";
    String pdfOutputSet = "src/main/resources/pdf/outSetMeta.pdf";

    @Test
    public void testGetMetaNoOut() throws IOException {
        PDFUtil pdfUtil = new PDFUtil(pdf, null, null);
        pdfUtil.getMeta();
    }

    @Test
    public void testGetMetaOut() throws IOException {
        PDFUtil pdfUtil = new PDFUtil(pdf, null, xmlTargetMustang);
        pdfUtil.getMeta();
        assertEquals(new File(xmlExpectedMustang).length(), new File(xmlTargetMustang).length());
    }

    @Test
    public void testSetMetaNoOut() throws COSVisitorException, ParserConfigurationException, TransformerException, SAXException, IOException {
        PDFUtil pdfUtil = new PDFUtil(pdfTargetSet, xmlToSet, null);
        pdfUtil.setMeta();
        assertEquals(new File(pdfExpectedSet).length(), new File(pdfTargetSet).length());
    }

    @Test
    public void testSetMetaOut() throws COSVisitorException, ParserConfigurationException, TransformerException, SAXException, IOException {
        PDFUtil pdfUtil = new PDFUtil(pdfTargetSet, xmlToSet, pdfOutputSet);
        pdfUtil.setMeta();
        assertEquals(new File(pdfTargetSet).length(), new File(pdfOutputSet).length());
    }
}