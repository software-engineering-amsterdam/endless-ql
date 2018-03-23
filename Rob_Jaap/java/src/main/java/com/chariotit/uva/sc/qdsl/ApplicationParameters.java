package com.chariotit.uva.sc.qdsl;

import org.apache.commons.cli.*;

public class ApplicationParameters {

    private static ApplicationParameters instance;
    private static String qlsArgumentShort = "s";
    private static String qlsArgumentLong = "style";
    private static String programName = "QLProgram";

    private String qlFilename;
    private String qlsFilename;


    private ApplicationParameters() {
    }

    public String getQlFilename() {
        return qlFilename;
    }

    private void setQlFilename(String qlFilename) {
        this.qlFilename = qlFilename;
    }

    public String getQlsFilename() {
        return qlsFilename;
    }

    private void setQlsFilename(String qlsFilename) {
        this.qlsFilename = qlsFilename;
    }

    public static ApplicationParameters get(String... args) throws InvalidParametersException {
        if (instance == null) {
            getFromCommandLine(args);
        }

        return instance;
    }

    public static void printHelp() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp(programName, getCommandLineOptions());
        System.exit(1);
    }

    private static Options getCommandLineOptions() {
        Options options = new Options();

        Option qlsOption = Option.builder(qlsArgumentShort)
                .required(false)
                .longOpt(qlsArgumentLong)
                .desc("Location of the QLS Stylesheet")
                .hasArg()
                .build();

        options.addOption(qlsOption);

        return options;
    }

    private static void getFromCommandLine(String... args) throws InvalidParametersException {
        ApplicationParameters applicationParameters = new ApplicationParameters();

        CommandLine commandLine;
        CommandLineParser parser = new DefaultParser();

        try {
            commandLine = parser.parse(getCommandLineOptions(), args);

            if (commandLine.hasOption(qlsArgumentLong)) {
                applicationParameters.setQlsFilename(
                        commandLine.getOptionValue(qlsArgumentLong)
                );
            }

            String[] remainder = commandLine.getArgs();

            if (remainder.length != 1) {
                throw new InvalidParametersException();
            }

            applicationParameters.setQlFilename(remainder[0]);
            instance = applicationParameters;

        } catch (Exception e) {
            throw new InvalidParametersException();
        }
    }
}
