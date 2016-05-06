package com.dataslang.xmlValidator;

import com.beust.jcommander.JCommander;

public class XmlValidator {

    private static final String VALIDATE_OPTION = "validate";
    private static final String TRANSFORM_OPTION = "transform";

    public static void main(String[] args){
        MainCommander com = new MainCommander();
        ValCommander val = new ValCommander();
        XsltCommander xsl = new XsltCommander();

        JCommander jct = null;
        try {
            jct = new JCommander(com, args);
            jct.addCommand("validate", val);
            jct.addCommand("transform", xsl);
        } catch (Exception e) {
            if(com.debug)
                e.printStackTrace();
            else
                System.out.println(e.getMessage());
            System.exit(1);
        }
        if(args.length == 0 || com.help){
            jct.usage();
            System.exit(0);
        }

        if(jct.getParsedCommand().equalsIgnoreCase(VALIDATE_OPTION)){
            Validator validator = new Validator(val.xml, val.xsd, com.debug);
            validator.validate();
        }

    }
}
