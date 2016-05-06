package com.dataslang.xmlValidator;

import com.beust.jcommander.Parameter;

public class MainCommander {
    @Parameter(names = {"--help", "-h"}, help = true, description = "Shows this help")
    public boolean help = false;

    @Parameter(names = {"--debug", "-d"}, description = "Debug mode")
    public boolean debug = false;
}
