package com.dataslang.xmlValidator.commander;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

@Parameters(commandDescription = "turns HTML file into PDF")
public class HtmlToPdfCommander {
    @Parameter(names = {"-i", "--input"}, required = true, description = "turns document into PDF")
    public String input;

    @Parameter(names = {"-o", "--output"}, required = true, description = "generate the PDF file into this destination")
    public String output;
}
