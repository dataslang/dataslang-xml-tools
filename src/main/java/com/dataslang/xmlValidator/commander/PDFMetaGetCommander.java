package com.dataslang.xmlValidator.commander;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

@Parameters(commandDescription = "Get metadata from PDF")
public class PDFMetaGetCommander {
    @Parameter(names = "--pdf", required = true, description = "PDF target")
    public String pdf;

    @Parameter(names = {"-o", "--output"}, description = "generate the XML file into this destination")
    public String output;
}
