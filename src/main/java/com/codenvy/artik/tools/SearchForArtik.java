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


public class SearchForArtik {
    List<String> foundIPs = new ArrayList();
    
    public List<String> search(String ip){
        return search(ip,20);
    }
    
    public List<String> search(String ip,int timeout){
        if(ip!=null){
            List<String> ipSearch = Arrays.asList(ip.split("\\."));
            if(ipSearch.size()>4){
                System.exit(1);
                return null;
            }
            if(ipSearch.size()==4){
                ipSearch.remove(4);
            }
            ip="";
            for (String string : ipSearch) {
                ip+=string+".";
            }
            findIPs(ip,timeout);
        }
        else{
             //findIPs("192.168.0.",timeout);
             findIPs("192.168.1.",timeout);
             //findIPs("192.168.2.",timeout);
        }
        return foundIPs;
    }
    
    private void findIPs(String ip,int timeout){
        for(int i=1;i<255;i++){
            if(tryConnection(ip+i,timeout)){
                foundIPs.add(ip);
            }
        }

    } 
    

    private boolean tryConnection(String ipAddress, int timeout){        
        int port = 22; // You'll need some sort of service to connect to
        boolean reachable = false;
        InetSocketAddress byAddress1 = new InetSocketAddress(ipAddress, port); 
        Socket socket = new Socket();
        String messageFail = null ;
        try {
          socket.connect(byAddress1, timeout);
        }
        catch (IOException ex) {
            if ( ex.getMessage().equals("Connection refused")) {
                messageFail = port + " on " + ipAddress + " is closed.";
            };
            if ( ex instanceof UnknownHostException ) {
                messageFail = ipAddress + " is unresolved.";
            }
            if ( ex instanceof SocketTimeoutException ) {
                messageFail = "Timeout " + ipAddress + " on port " + port;
            }
        }finally {
            if (socket != null) {
                if ( socket.isConnected()) {
                    System.out.println(port + " on " + ipAddress + " reachable");
                    reachable = true;
                } else {
                    //System.out.println(port + " on " + ipAddress + " not reachable; reason: " + messageFail );
                }
                try {
                    socket.close();
                } catch (IOException e) {
                }
            }
        }
        return reachable;
    }
    
    public void networkInformation(){
        try{
            Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
            for (NetworkInterface netint : Collections.list(nets)){
                System.out.println("Display name: " + netint.getDisplayName());
                System.out.println("Hardware address: " + Arrays.toString(netint.getHardwareAddress()));
                Enumeration<InetAddress> ips = netint.getInetAddresses();
                for (InetAddress ip : Collections.list(ips)){
                    System.out.println("Ip Address: " + ip.getHostAddress());
                }

            }
            
        }catch(SocketException ex){
            
        }
    }
}
