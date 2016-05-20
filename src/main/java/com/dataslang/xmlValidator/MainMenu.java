package com.dataslang.xmlValidator;

import com.beust.jcommander.JCommander;
import com.dataslang.xmlValidator.actions.HTMLUtil;
import com.dataslang.xmlValidator.actions.PDFUtil;
import com.dataslang.xmlValidator.actions.XMLUtil;
import com.dataslang.xmlValidator.commander.*;

public class MainMenu {

    private static final String XML_VALIDATE_OPTION = "xmlvalidate";
    private static final String XML_TRASFORM_OPTION = "xmltrasform";
    private static final String HTML_TRASFORM_PDF_OPTION = "htmltrasform";
    private static final String PDF_META_SET_OPTION = "pdfmetaset";
    private static final String PDF_META_GET_OPTION = "pdfmetaget";
    private static final String PDF_VALIDATE_OPTION = "pdfvalidate";

    public static void main(String[] args) {
        MainCommander com = new MainCommander();

        XMLValidateCommander xmlValidateCommander = new XMLValidateCommander();
        XMLTrasformCommander xmlTrasformCommander = new XMLTrasformCommander();
        HTMLTrasformCommander htmlTrasformCommander = new HTMLTrasformCommander();
        PDFMetaSetCommander pdfMetaSetCommander = new PDFMetaSetCommander();
        PDFMetaGetCommander pdfMetaGetCommander = new PDFMetaGetCommander();
        PDFValidateCommander pdfValidateCommander = new PDFValidateCommander();

        JCommander jct = null;

        try {

            jct = new JCommander(com);
            jct.addCommand(XML_VALIDATE_OPTION, xmlValidateCommander);
            jct.addCommand(XML_TRASFORM_OPTION, xmlTrasformCommander);
            jct.addCommand(HTML_TRASFORM_PDF_OPTION, htmlTrasformCommander);
            jct.addCommand(PDF_META_SET_OPTION, pdfMetaSetCommander);
            jct.addCommand(PDF_META_GET_OPTION, pdfMetaGetCommander);
            jct.addCommand(PDF_VALIDATE_OPTION, pdfValidateCommander);

            jct.parse(args);

        } catch (Exception e) {
            if (com.debug) {
                e.printStackTrace();
                System.exit(1);
            } else {
                System.out.println(e.getMessage());
                System.exit(1);
            }
        }

        if (com.help) {
            jct.usage();
            System.exit(0);
        }

        try {

            if(jct.getParsedCommand().equalsIgnoreCase(XML_VALIDATE_OPTION)){
                XMLUtil xmlUtil = new XMLUtil(xmlValidateCommander.xml, xmlValidateCommander.xsd);
                xmlUtil.validate();
            }else if(jct.getParsedCommand().equalsIgnoreCase(XML_TRASFORM_OPTION)){
                XMLUtil xmlUtil = new XMLUtil(xmlTrasformCommander.xml, xmlTrasformCommander.xslt, xmlTrasformCommander.output);
                xmlUtil.transformate();
            }else if(jct.getParsedCommand().equalsIgnoreCase(HTML_TRASFORM_PDF_OPTION)){
                HTMLUtil htmlUtil = new HTMLUtil(htmlTrasformCommander.input, htmlTrasformCommander.output);
                htmlUtil.transform();
            }else if(jct.getParsedCommand().equalsIgnoreCase(PDF_META_SET_OPTION)){
                PDFUtil pdfUtil = new PDFUtil(pdfMetaSetCommander.pdf, pdfMetaSetCommander.xml, pdfMetaSetCommander.output);
                pdfUtil.setMeta();
            }else if(jct.getParsedCommand().equalsIgnoreCase(PDF_META_GET_OPTION)){
                PDFUtil pdfUtil = new PDFUtil(pdfMetaGetCommander.pdf, null, pdfMetaGetCommander.output);
                pdfUtil.getMeta();
            }else if(jct.getParsedCommand().equalsIgnoreCase(PDF_VALIDATE_OPTION)){
                PDFUtil pdfUtil = new PDFUtil(pdfValidateCommander.pdf, null, null);
                pdfUtil.validatePDF();
            }

        } catch (NullPointerException e) {
            jct.usage();
        } catch (Exception e){
            if (com.debug)
                e.printStackTrace();
            else
                System.out.println(e.getMessage());
        }
    }
}
