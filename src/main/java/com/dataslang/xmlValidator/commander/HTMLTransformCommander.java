package com.dataslang.xmlValidator.commander;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

@Parameters(commandDescription = "Turns HTML file into PDF")
public class HTMLTransformCommander {
    @Parameter(names = "--html", required = true, description = "HTML file to change")
    public String input;

    @Parameter(names = {"-o", "--output"}, required = true, description = "Generate the PDF into this destination")
    public String output;

    @Parameter(names = {"-s", "--strategy"}, description = "Uses different methods, just pick one (1,2,3,4)")
    public int version = 3;
}
