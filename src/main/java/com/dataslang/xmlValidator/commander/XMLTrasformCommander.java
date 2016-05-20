package com.dataslang.xmlValidator.commander;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

@Parameters(commandDescription = "Transform the XML by the logic of the XSLT")
public class XMLTrasformCommander {
    @Parameter(names = "--xml", required = true, description = "XML target file")
    public String xml;

    @Parameter(names = "--xslt", required = true, description = "XSLT file")
    public String xslt;

    @Parameter(names = {"-o", "--output"}, description = "Generate the XML file into this destination")
    public String output;
}
