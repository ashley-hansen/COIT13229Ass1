package com.coit13229ass1;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;

public class UDPServer {

    public static void main(String[] args) throws SocketException, IOException, ClassNotFoundException {
        //initialise variable to creak socket connection
        int port = 2276;
        DatagramSocket serverSocket = new DatagramSocket(port);
        Scanner scanner = new Scanner(System.in);
        ArrayList<Member> memberList = new ArrayList<Member>();// arraylist for object deserialisation
        //while loop to keep connection open
        while (true) {
            byte[] recieveBuffer = new byte[1024];
            byte[] sendBuffer = new byte[1024];

            //create datagram object to recieve message
            DatagramPacket recievePacket = new DatagramPacket(recieveBuffer, recieveBuffer.length);
            serverSocket.receive(recievePacket);
            InetAddress address = recievePacket.getAddress();
            int portNum = recievePacket.getPort();

            //create string onject from client to bytestream and perform action
            //empty string response and server will return the list.
            String clientMessage = new String(recievePacket.getData());
            System.out.println("\nCLient response recieved " + clientMessage);
            System.out.println("\nServer : performing required action");

            //create object from serialised memberlistObject file
            FileInputStream sIn = new FileInputStream("memberListObject");
            ObjectInputStream sOut = new ObjectInputStream(sIn);
            memberList = (ArrayList) sOut.readObject();

            //build stringbuilder object a newline string
            StringBuilder sb = new StringBuilder();
            String newLine = "\n";

            //create string for message
            for (int i = 0; i < memberList.size(); i++) {
                sb.append(memberList.get(i) + newLine);
            }
            String str = sb.toString();

            //create bytestream to send list back to server with outgoing datagram object
            sendBuffer = str.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, address, portNum);
            serverSocket.send(sendPacket);//send the list message
            //close if message recieved is equal to bye
            if (clientMessage.equalsIgnoreCase("close")) {
                System.out.println("Connection ended by server");
                break;
            }
        }

        serverSocket.close();//close socket connection

    }
}
