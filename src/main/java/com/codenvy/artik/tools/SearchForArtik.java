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
import java.util.Iterator;
import java.util.LinkedList;

import java.net.InetAddress;
import java.net.NetworkInterface;


public class SearchForArtik {
    List<String> foundIPs = new ArrayList<String>();
        
    public List<String> search(String ip,int timeout){
        foundIPs = new ArrayList<String>();

        List<String> ipSearch = new LinkedList<String>(Arrays.asList(ip.split("\\.")));
        if(ipSearch.size()>4){
            System.exit(1);
            return null;
        }
        if(ipSearch.size()==4){
            ipSearch.remove(3);
        }
        ip="";
        for (String string : ipSearch) {
            ip+=string+".";
        }
        findIPs(ip,timeout);
        return foundIPs;
    }
    
    private void findIPs(String ip,int timeout){
        System.out.println("Searching ipaddresses "+ip+"1-254 with open port 22");
        System.out.print("Scanning");
        for(int i=1;i<255;i++){
            if( i==25 ||  i==50 ||  i==75 ||  i==100 ||  i==125 ||  i==150 ||  i==175 ||  i==200 || i==225  ){
                System.out.print(".");
            }
            if(tryConnection(ip+i,timeout)){
                foundIPs.add(ip+i);
            }
        }
        System.out.println(" Finished");
        System.out.println("Found ("+foundIPs.size()+") host(s) using ipaddresses "+ip+"1-254 with open port 22");
        ipsFound();
        System.out.println("");
    } 
    

    private boolean tryConnection(String ipAddress, int timeout){        
        int port = 22; // You'll need some sort of service to connect to
        boolean reachable = false;
        InetSocketAddress byAddress1 = new InetSocketAddress(ipAddress, port); 
        Socket socket = new Socket();
        
        try {
          socket.connect(byAddress1, timeout);
        }
        catch (IOException ex) {
//            String messageFail = null ;
//            if (ex.getMessage()!=null){
//                if ( ex.getMessage().equals("Connection refused")) {
//                    messageFail = port + " on " + ipAddress + " is closed.";
//                };
//                if ( ex instanceof UnknownHostException ) {
//                    messageFail = ipAddress + " is unresolved.";
//                }
//                if ( ex instanceof SocketTimeoutException ) {
//                    messageFail = "Timeout " + ipAddress + " on port " + port;
//                }
//            }
        }finally {
            if (socket != null) {
                if ( socket.isConnected()) {
                    //System.out.println(port + " on " + ipAddress + " reachable");
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
    
    public void ipsFound(){
        Iterator<String> iterator = foundIPs.iterator(); 
        // check values
        while (iterator.hasNext()){
            System.out.println(iterator.next());  
        } 
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
