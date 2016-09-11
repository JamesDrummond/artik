package com.codenvy.artik.tools;

import java.io.IOException;
import java.io.PrintStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class OpenSSH {
    public void connect(String ip){
//        try{
//            System.out.println("Starting ssh:  ");
//            //String COMMAND = "sshpass -p 'root' ssh root@"+ip+" 'echo inside && exit'";
//            //String[] SHELL_COMMAND = { "/bin/sh", "-c", COMMAND };
//            //Runtime runtime = Runtime.getRuntime();
//            //Process p = runtime.exec(SHELL_COMMAND);
//            //String[] cmdarray = {"sshpass", "-p", "'root'", "ssh", "root@"+ip, "'echo inside artik'"};
//            String[] cmdarray = {"ls","/"};
//            Process p = Runtime.getRuntime().exec(cmdarray); 
//            //Process p = Runtime.getRuntime().exec("/usr/bin/sshpass -p 'root' /usr/bin/ssh -o StrictHostKeyChecking=no root@"+ip);
//            //PrintStream out = new PrintStream(p.getOutputStream());
//            //BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
//            System.out.println("1");
////            
////            while (in.ready()) {
////                System.out.println("2");
////              String s = in.readLine();
////              System.out.println(s);
////            }
////            System.out.println("3");
////            out.println("echo Successfully entered ARTIK. Running ifconfig.");
////            out.println("uname -a");
////            out.println("exit");
//            p.waitFor();
//        }catch(InterruptedException e){
//            System.out.println(e);
//        }catch(IOException e){
//            System.out.println(e);
//        }

        final ProcessBuilder pb = new ProcessBuilder("/bin/ls -l /");
        //final ProcessBuilder pb = new ProcessBuilder("/usr/bin/sshpass","-p","'root'","/usr/bin/ssh","-o","StrictHostKeyChecking=no","root@"+ip);
        //final ProcessBuilder pb = new ProcessBuilder("/bin/echo","/usr/bin/sshpass","-p","'root'","/usr/bin/ssh","-o","StrictHostKeyChecking=no","root@"+ip);
        pb.redirectInput(ProcessBuilder.Redirect.INHERIT);
        pb.redirectOutput(ProcessBuilder.Redirect.INHERIT);
        try{
            System.out.println("Starting ssh:  ");
            final Process p = pb.start();
            p.waitFor();
        }catch(IOException e){
            System.out.println(e.getMessage());
        }catch(InterruptedException e){
            System.out.println(e.getMessage());
        }
    }
}
