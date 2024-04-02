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

        try {
            File memberFile = new File("memberlist.txt");
            PrintStream writer = new PrintStream(memberFile);
            Member member1 = (Member) input.readObject();

            name = member1.getName();
            writer.println(name);
            address = member1.getAdress();
            writer.println(address);
            phone = member1.getPhone();
            writer.println(phone);
            ArrayList<Member> memberList = new ArrayList<Member>();

            memberList.add(new Member(name, address, phone));
            for (int i = 0; i < memberList.size(); i++) {
                System.out.println(memberList.size());
            }

//            output.writeObject(member1);
        } catch (IOException e) {
            System.out.println("IOException" + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found exception" + e.getMessage());
        }
    }

}
