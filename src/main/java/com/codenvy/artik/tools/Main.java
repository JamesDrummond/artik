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
import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.ParseException;

import java.net.InetAddress;
import java.net.NetworkInterface;


public class Main {
    public static void main(String[] args) {
        boolean showNetworkInformation=false;
        System.out.println("Make sure artik board is on and connected to network then press <ENTER>:");
        Scanner reader = new Scanner(System.in);  // Reading from System.in
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try{br.readLine();}catch(IOException ex){}
        
        ParseArgs parser = new ParseArgs();
        parser.parse(args);
        if(parser.search){
            SearchForArtik sa = new SearchForArtik();
            if(showNetworkInformation){//not used right now
                sa.networkInformation();
            }
            HashSet<String> foundIPs1 = new HashSet<String>(sa.search(parser.ip));
            System.out.println("Found ("+foundIPs1.size()+") hosts using .");
            System.out.println("Disconnect artik board cat5 cable then press <ENTER>:");
            try{br.readLine();}catch(IOException ex){}
            HashSet<String> foundIPs2 = new HashSet<String>(sa.search(parser.ip));
            System.out.println("Found ("+foundIPs2.size()+") hosts using .");
            
            foundIPs1.removeAll(foundIPs2);
            if(foundIPs1.size()>0){
                System.out.println("Possible ip addresses for artik board.");
                // create an iterator
                Iterator<String> iterator = foundIPs1.iterator(); 
                // check values
                while (iterator.hasNext()){
                    System.out.println(iterator.next());  
                } 
            }
        }
        
    }
    
}