package com.coit13229ass1;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;

public class TCPServer {

    public static void main(String[] args) throws FileNotFoundException, IOException {

        try{
			int serverPort = 8000; 
			ServerSocket listenSocket = new ServerSocket(serverPort);
			
			while(true) {
				Socket clientSocket = listenSocket.accept();
				Connection c = new Connection(clientSocket);
			}

        } catch (IOException e) {

        }
    }//End Main method

    

}//End of class

class Connection extends Thread {

    ObjectInputStream input;
    ObjectOutputStream output;
    Socket clientSocket;

    public Connection(Socket aClientSocket) {

        try {
            clientSocket = aClientSocket;
            input = new ObjectInputStream(clientSocket.getInputStream());
            output = new ObjectOutputStream(clientSocket.getOutputStream());
            System.out.println("Step 2");
            this.start();
        } catch (IOException e) {
            System.out.println("Connection:" + e.getMessage());
        }
        
        
        }
    public void run(){
            try{
               System.out.println("Step 3");
               Member member1 = (Member)input.readObject();
               System.out.println(member1);
               output.writeObject(member1);
               
             
             
            }catch(IOException e){
                System.out.println("IOException" + e.getMessage());
            }catch(ClassNotFoundException e){
                System.out.println("Class not found exception" + e.getMessage());
            }
          
    }
}
