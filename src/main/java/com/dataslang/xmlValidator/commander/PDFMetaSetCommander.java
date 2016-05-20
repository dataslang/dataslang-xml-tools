package com.dataslang.xmlValidator.commander;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

@Parameters(commandDescription = "Set metadata into PDF")
public class PDFMetaSetCommander {
    @Parameter(names = "--pdf", required = true, description = "PDF target")
    public String pdf;

    @Parameter(names = "--xml", required = true, description = "XML metadata file")
    public String xml;

    @Parameter(names = {"-o", "--output"}, description = "Output file, if not added will override the PDF target")
    public String output;
}
