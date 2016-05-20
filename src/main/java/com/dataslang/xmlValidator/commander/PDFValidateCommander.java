package com.dataslang.xmlValidator.commander;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

@Parameters(commandDescription = "Validates the given PDF")
public class PDFValidateCommander{
    @Parameter(names = "--pdf", required = true, description = "PDF target")
    public String pdf;
}
