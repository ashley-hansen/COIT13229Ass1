package com.coit13229ass1;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;

public class UDPServer {
        public static void main(String[] args) throws SocketException, IOException {

        int port = 2276;
        DatagramSocket serverSocket = new DatagramSocket(port);
        Scanner scanner = new Scanner(System.in);

        while(true){
            byte[] recieveBuffer = new byte[1024];
            byte[] sendBuffer = new byte[1024];

            DatagramPacket recievePacket = new DatagramPacket(recieveBuffer,recieveBuffer.length);
            serverSocket.receive(recievePacket);
            InetAddress address = recievePacket.getAddress();
            int portNum = recievePacket.getPort();

            String clientMessage = new String(recievePacket.getData());
            System.out.println("\nCLient: "+clientMessage);
            System.out.println("\nServer :");
            String serverMessage = scanner.nextLine();
            sendBuffer = serverMessage.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendBuffer,sendBuffer.length, address,portNum);
            serverSocket.send(sendPacket);
            if(serverMessage.equalsIgnoreCase("bye")){
                System.out.println("Connection ended by server");
                break;
            }
        }

        serverSocket.close();

    }
}



//        ArrayList<Member> memberList = new ArrayList<Member>();
//        
//        try{
//        FileInputStream sIn = new FileInputStream("memberListObject");
//        ObjectInputStream sOut = new ObjectInputStream(sIn);
//        
//        memberList = (ArrayList) sOut.readObject();
//        
//        System.out.println(memberList);
//        }catch(Exception e){
//        }