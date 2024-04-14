package com.coit13229ass1;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;

public class TCPServer {

    public static void main(String[] args) throws FileNotFoundException, IOException {

        try {
            //Set Socket port number
            int serverPort = 1176;
            ServerSocket listenSocket = new ServerSocket(serverPort);
            //create connection object utilising thread for multiple concurrent connections
            while (true) {
                Socket clientSocket = listenSocket.accept();
                ClientConnection connection = new ClientConnection(clientSocket);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }//End Main method

}//End of class

class ClientConnection extends Thread {
    //initialise in and out data streams
    ObjectInputStream input;
    ObjectOutputStream output;
    Socket clientSocket;

    public ClientConnection(Socket aClientSocket) {

        try {
            clientSocket = aClientSocket;
            input = new ObjectInputStream(clientSocket.getInputStream());
            output = new ObjectOutputStream(clientSocket.getOutputStream());
            //starts server thread
            this.start();
        } catch (IOException e) {
            System.out.println("Connection:" + e.getMessage());
        }

    }
    //run method starts thread
    public void run() {

        boolean running = true;
        String name;
        String address;
        String phone;
        String line;
        ArrayList<Member> memberList = new ArrayList<Member>();
        ArrayList<String>list = new ArrayList<String>();

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
            
            while((line = reader.readLine()) != null){
               list.add(line);
                
            }
            reader.close(); //Closes reader
            
            System.out.println("New member recieved printing member list"); //adds all lines of textfile to a array list of strings used
            for (int i = 0; i < list.size(); i = i+3){   //to assign to string variable to parss in arrylist objects
                name= list.get(i);
                address = list.get(i+1);
                phone = list.get(i+2);
                
                memberList.add(new Member(name, address, phone));
                                
            }
            // prints array list of objects for verification.
            for (int i = 0; i < memberList.size();i++){
                System.out.println(memberList.get(i));
            }
            // serialise arraylist objects
             FileOutputStream fileOut = new FileOutputStream("memberlistObject");
                ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
                objectOut.writeObject(memberList);
                
        } catch (IOException e) {
            System.out.println("IOException" + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found exception" + e.getMessage());
        }
    }

}
