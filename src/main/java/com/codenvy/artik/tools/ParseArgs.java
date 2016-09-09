package com.codenvy.artik.tools;
import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;

import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.ParseException;

import java.net.InetAddress;
import java.net.NetworkInterface;

public class ParseArgs {
    public String ip;
    boolean search;
    
    public ParseArgs(){}
    
    public void parse(String[] args){
        Options options = new Options();
        
        Option opIP = new Option("i", "ipaddress", true, "Subnet ID (ei 192.168.1.0) ");
        opIP.setRequired(false);
        options.addOption(opIP);
        
        Option opSearch = new Option("s", "search", false, "Search for Artik on local network. Will search 192.168.0-2 if '--ipaddress' is not set.");
        opSearch.setRequired(false);
        options.addOption(opSearch);

        CommandLineParser parser = new GnuParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd;

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("utility-name", options);

            System.exit(1);
            return;
        }

        boolean search = cmd.hasOption("search");
        ip = cmd.getOptionValue("ipaddress");
        
        if(!search){//This will change when more options are added.
            search=true;
            //System.exit(1);
            //return;
        }
    }
}
