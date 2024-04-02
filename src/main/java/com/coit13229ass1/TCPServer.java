package com.coit13229ass1;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class TCPServer {

    public static void main(String[] args) {
           Socket socket = null;
           InputStreamReader input = null;
           OutputStreamWriter output = null;
           BufferedReader br = null;
           BufferedWriter bw = null;
           ServerSocket sSocket = null;
           
        try {
            sSocket = new ServerSocket(8000);
            
            while(true){
             socket = sSocket.accept();
             
            }
        } catch (IOException ex) {
            Logger.getLogger(TCPServer.class.getName()).log(Level.SEVERE, null, ex);
        }
           
           
           
    }
}
