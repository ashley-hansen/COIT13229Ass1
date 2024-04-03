package com.coit13229ass1;

import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TCPClient {

    public static void main(String[] args) {
        Socket socket = null;
        InputStreamReader inputStream = null;
        OutputStreamWriter outputStream = null;
        BufferedReader br = null;
        BufferedWriter bw = null;
        
        try {
            socket = new Socket("localhost", 8000);
            
            inputStream = new InputStreamReader(socket.getInputStream());
            outputStream = new OutputStreamWriter(socket.getOutputStream());
            
           br = new BufferedReader(inputStream);
           bw = new BufferedWriter(outputStream);
            
            
            Scanner scanner = new Scanner(System.in);
            
        } catch (IOException ex) {
            Logger.getLogger(TCPClient.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
