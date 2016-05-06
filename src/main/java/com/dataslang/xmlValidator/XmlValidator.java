package com.dataslang.xmlValidator;

import com.beust.jcommander.JCommander;

public class XmlValidator {

    public static void main(String[] args){
        MainCommander com = new MainCommander();
        JCommander jct = null;

        ValCommander val = new ValCommander();
        jct.addCommand("validate", val);
        XsltCommander xsl = new XsltCommander();
        jct.addCommand("transform", xsl);
        try {
            jct = new JCommander(com, args);
        } catch (Exception e) {
            if(com.debug)
                e.printStackTrace();
            else
                System.out.println(e.getMessage());
            System.exit(1);
        }
        if(args.length == 0 || com.debug){
            jct.usage();
            System.exit(0);
        }
        Validator validator = new Validator(val.xml, val.xsd, com.debug);
        validator.validate();
    }
}
