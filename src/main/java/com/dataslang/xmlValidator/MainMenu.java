package com.dataslang.xmlValidator;

import com.beust.jcommander.JCommander;
import com.dataslang.xmlValidator.actions.PdfEditor;
import com.dataslang.xmlValidator.actions.Transformator;
import com.dataslang.xmlValidator.actions.Validator;
import com.dataslang.xmlValidator.commander.*;

public class MainMenu {

    private static final String VALIDATE_OPTION = "validate";
    private static final String TRANSFORM_OPTION = "transform";
    private static final String META_OPTION = "meta";
    private static final String HTML_OPTION = "html";

    public static void main(String[] args) {
        MainCommander com = new MainCommander();
        ValCommander val = new ValCommander();
        XsltCommander xsl = new XsltCommander();
        MetaCommander dat = new MetaCommander();
        HtmlToPdfCommander html = new HtmlToPdfCommander();


        JCommander jct = null;

        try {
            jct = new JCommander(com);
            jct.addCommand(HTML_OPTION, html);
            jct.addCommand(VALIDATE_OPTION, val);
            jct.addCommand(TRANSFORM_OPTION, xsl);
            jct.addCommand(META_OPTION, dat);
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
            if (jct.getParsedCommand().equalsIgnoreCase(VALIDATE_OPTION)) {
                Validator validator = new Validator(val.xml, val.xsd);
                validator.validate();
            } else if (jct.getParsedCommand().equalsIgnoreCase(TRANSFORM_OPTION)) {
                Transformator transformator = new Transformator(xsl.xml, xsl.xslt, xsl.output);
                transformator.transformate();
            } else if (jct.getParsedCommand().equalsIgnoreCase(META_OPTION)) {
                PdfEditor pdfEditor = new PdfEditor(dat.pdf, dat.output, dat.meta);
                pdfEditor.meta();
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
