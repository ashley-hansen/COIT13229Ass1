package com.coit13229ass1;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;

public class TCPServer {

    public static void main(String[] args) throws FileNotFoundException, IOException {

        try {
            int serverPort = 8000;
            ServerSocket listenSocket = new ServerSocket(serverPort);

            while (true) {
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

            this.start();
        } catch (IOException e) {
            System.out.println("Connection:" + e.getMessage());
        }

    }

    public void run() {

        boolean running = true;
        String name;
        String address;
        String phone;
        String line;
        ArrayList<Member>memberList = new ArrayList<Member>();

        try {
            //Create file and writer opbject
            FileWriter memberFile = new FileWriter("memberlist.txt", true);
            BufferedWriter writer = new BufferedWriter(memberFile);
            Member member1 = (Member) input.readObject();
            FileReader fileReader = new FileReader("memberlist.txt");
            BufferedReader reader = new BufferedReader(fileReader);
                    
            //Writes to text file
            name = member1.getName();
            writer.write(name);
            writer.newLine();
            address = member1.getAdress();
            writer.write(address);
            writer.newLine();
            phone = member1.getPhone();
            writer.write(phone);
            writer.newLine();
            writer.flush();
            writer.close();
            
            //send member name back to client for feedback
            output.writeObject(member1);
            line = reader.readLine();
//            while(line != null){
//                name = line;
//                address = line;
//                phone = line;
//                System.out.println(name+" "+address+" "+phone);
//            }
            
            
            
        } catch (IOException e) {
            System.out.println("IOException" + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found exception" + e.getMessage());
        }
    }

}
