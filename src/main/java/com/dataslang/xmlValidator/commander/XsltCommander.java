package com.dataslang.xmlValidator.commander;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

@Parameters(commandDescription = "Transform the xml by the logic of the xslt")
public class XsltCommander {
    @Parameter(names = "--xml", required = true, description = "XML target file")
    public String xml;

    @Parameter(names = "--xslt", required = true, description = "XSLT file")
    public String xslt;

    @Parameter(names = {"-o", "--output"}, description = "generate the XML file into this destination")
    public String output;
}