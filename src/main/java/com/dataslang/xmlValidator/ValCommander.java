package com.dataslang.xmlValidator;

import com.beust.jcommander.Parameter;

public class ValCommander {
    @Parameter(names = {"--help", "-h"}, help = true)
    public boolean help;

    @Parameter(names = {"--debug", "-d"}, description = "Debug mode")
    public boolean debug = false;

    @Parameter(names = "--xml", required = true, description = "XML target file")
    public String xml;

    @Parameter(names = "--xsd", required = true, description = "XSD target file")
    public String xsd;
}
