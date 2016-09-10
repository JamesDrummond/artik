package com.codenvy.artik.tools;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Main {
    public static void main(String[] args) {
        int timeout;
        boolean showNetworkInformation=false;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ParseArgs parser = new ParseArgs();
        List<String> ips = new ArrayList<String>();
        HashSet<String> foundIPs1 = new HashSet<String>();
        HashSet<String> foundIPs2 = new HashSet<String>();
        Iterator<String> iterator; 
        
        parser.parse(args);
        if(parser.search){
            SearchForArtik sa = new SearchForArtik();
            if(showNetworkInformation){//not used right now
                sa.networkInformation();
            }
            if(parser.ip==null){
                ips.add("192.168.0.");
                ips.add("192.168.1.");
                ips.add("192.168.2.");
            }
            else{
                ips.add(parser.ip);
            }
            timeout=parser.timeout;
            
            iterator = ips.iterator();
            if(!parser.quick){
                System.out.println("Make sure ARTIK board is on and connected to network then press <ENTER>:");
                try{br.readLine();}catch(IOException ex){}
            }

            while (iterator.hasNext()){
                String ipSearch = iterator.next();
                foundIPs1.addAll(sa.search(ipSearch,timeout));
            }
            
            if(!parser.quick){
                iterator = ips.iterator();
                System.out.println("Disconnect ARTIK board cat5 cable then press <ENTER>:");
                try{br.readLine();}catch(IOException ex){}
                while (iterator.hasNext()){
                    String ipSearch = iterator.next();
                    foundIPs2.addAll(sa.search(ipSearch,timeout));
                }
            }


            
            foundIPs1.removeAll(foundIPs2);//removes matches
            if(foundIPs1.size()>0){
                System.out.println("Possible ip addresses for ARTIK board.");
                iterator = foundIPs1.iterator(); 
                while (iterator.hasNext()){
                    System.out.println(iterator.next());  
                } 
            }
            else{
                System.out.println("Could not find ipaddress of ARTIK board on local network.");
            }
        }
        
    }
    
}