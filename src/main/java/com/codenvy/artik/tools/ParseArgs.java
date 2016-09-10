package com.codenvy.artik.tools;

import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.ParseException;


public class ParseArgs {
    public String ip;
    public int timeout;
    boolean search;
    boolean quick;
    
    public ParseArgs(){}
    
    public void parse(String[] args){
        Options options = new Options();
         
        String header = "Useful tools for ARTIK board\n\n";
        String footer = "\nPlease report issues at https://github.com/JamesDrummond/artik/issues";
         
        HelpFormatter formatter = new HelpFormatter();
         
        Option opHelp = new Option("h", "help", false, "This help information ");
        opHelp.setRequired(false);
        options.addOption(opHelp);
        
        Option opVersion = new Option("v", "version", false, "Version ");
        opVersion.setRequired(false);
        options.addOption(opVersion);
        
        Option opIP = new Option("i", "ipaddress", true, "Provide a ipaddress of network that ARTIK board is on. Generally host machine ipaddress. Will search 192.168.0-2 if '--ipaddress' is not set.");
        opIP.setRequired(false);
        options.addOption(opIP);
        
        Option opTimeout = new Option("t", "timeout", true, "Timeout for each port (default 20ms) ");
        opTimeout.setRequired(false);
        options.addOption(opTimeout);
        
        Option opQuick = new Option("q", "quick", false, "Quick scan of all open ports on port 22. No prompt for disconnect.");
        opQuick.setRequired(false);
        options.addOption(opQuick);
        
        //Option opSearch = new Option("s", "search", false, "Search for Artik on local network. Will search 192.168.0-2 if '--ipaddress' is not set.");
        //opSearch.setRequired(false);
        //options.addOption(opSearch);

        CommandLineParser parser = new GnuParser();
        CommandLine cmd;

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("docker run -i jdrummond/artik-tools", header, options, footer, true);
            System.exit(1);
            return;
        }
        
        if(cmd.hasOption("help")){
            formatter.printHelp("docker run -i jdrummond/artik-tools", header, options, footer, true);
            System.exit(1);
        }
        
        if(cmd.hasOption("version")){
            System.out.println((new Version()).getVersion());
            System.exit(1);
        }

        ip = cmd.getOptionValue("ipaddress");
        try {
            timeout = Integer.parseInt(cmd.getOptionValue("timeout"));
        }catch(NumberFormatException ex){
            System.out.println("Default timeout 20ms being used.");
            timeout = 20;
        }
        search = cmd.hasOption("search");
        if(!search){//This will change when more options are added.
            search=true;
            //System.exit(1);
            //return;
        }
        quick = cmd.hasOption("quick");
    }
}
