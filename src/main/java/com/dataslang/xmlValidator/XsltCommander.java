package com.dataslang.xmlValidator;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

@Parameters(commandDescription = "Transform the xml by the logic of the xslt")
public class XsltCommander {
    @Parameter(names = "--xml", required = true, description = "XML target file")
    public String xml;

    @Parameter(names = "--xslt", required = true, description = "XSLT file")
    public String xsd;
}
