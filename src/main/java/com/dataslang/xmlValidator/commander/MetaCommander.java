package com.dataslang.xmlValidator.commander;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

@Parameters(commandDescription = "Get metadata from PDF")
public class MetaCommander {
    @Parameter(names = "--pdf", required = true, description = "Exctract metadata from PDF")
    public String pdf;

    @Parameter(names = {"-o", "--output"}, description = "generate the PDF file into this destination")
    public String output;

    @Parameter(names = {"-m", "--meta"}, description = "if this parameter is added, the metadata will be inserted from this path instead of extracting it")
    public String meta;

    @Parameter(names = {"-v", "--validate"}, description = "if checked, this will only validate the file", arity = 0)
    public boolean validate;
}
