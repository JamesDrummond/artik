package com.codenvy.artik.tools;
import java.io.IOException;
import java.net.UnknownHostException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class Main {
    public static void main(String[] argvs) {
        String ipAddress = "192.168.1.6"; // Add your own
        final int timeout = 2000;
        int port = 22; // You'll need some sort of service to connect to
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
                } else {
                    System.out.println(port + " on " + ipAddress + " not reachable; reason: " + messageFail );
                }
                try {
                    socket.close();
                } catch (IOException e) {
                }
            }
        }


        String a = "Che12";
        System.out.println("Hello World " + a + "!");
    }
}