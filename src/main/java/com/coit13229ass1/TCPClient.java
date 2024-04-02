package com.coit13229ass1;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class TCPClient {

    public static void main(String[] args) {
        dataEntry();
    }// end of main method
    public static void establishConnection(){
    
    }
    public static void dataEntry() {
        boolean isNext = true;

        while (isNext) {

            try {
                Member member = new Member();
                InputStreamReader is = new InputStreamReader(System.in);
                BufferedReader input = new BufferedReader(is);
                
                //Set Socket port number
                int portNumber = 8000;
                
                //Initialise a socket variable to send a revice comms on a particular port
                Socket soc = new Socket("localhost", portNumber);
                
                //Initialises socket connections for in and out comunication
                ObjectInputStream socIn = null;
                ObjectOutputStream socOut = null;
                socIn = new ObjectInputStream(soc.getInputStream());
                socOut = new ObjectOutputStream(soc.getOutputStream());
                
                
                //Set member object details and set to server on desginated socket
                System.out.println("Enter name for member:");
                String name = input.readLine(); member.setName(name);
                System.out.println("Enter address for member:");
                String address = input.readLine(); member.setAdress(address);
                System.out.println("Enter phone for member:");
                String phone = input.readLine(); member.setPhone(phone);
                System.out.println("Member "+member.getName()+" Created");
                socOut.writeObject(member);
                
                //ask user if they would like to enter another member
                System.out.println("would you like to enter details for another member?: Type 'n' to exit or anyother key to continue");
                String cont = input.readLine();
                cont = cont.toLowerCase();
                System.out.println(cont);
                
                //break loop if response is "n" for no
                if (cont.equals("n")) {
                isNext = false;
            }

            
            }catch(IOException e){
                e.printStackTrace();
                System.out.println("Please check that the server is running. Clent application has been closed.");
                break;
            }

        }
    }//End of dataEntry method
}//end of class
