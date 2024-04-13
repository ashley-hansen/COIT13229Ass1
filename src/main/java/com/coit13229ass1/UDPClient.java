package com.coit13229ass1;

import java.net.*;
import java.io.*;
import java.util.Scanner;


public class UDPClient {
        public static void main(String[] args) throws IOException, SocketException {
        int port = 2276;
        Scanner scanner = new Scanner(System.in);
        InetAddress address = InetAddress.getByName("localhost");
        DatagramSocket clientSocket = new DatagramSocket();

        while(true){

            byte[] sendBuffer = new byte[1024];
            byte[] recieveBuffer = new byte[1024];

            System.out.println("\nClient: ");
            String clientMessage = scanner.nextLine();
            sendBuffer = clientMessage.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendBuffer,sendBuffer.length,address, port);
            clientSocket.send(sendPacket);

            if(clientMessage.equalsIgnoreCase("bye")){
                System.out.println("Connection ended by client");
                break;
            }

            DatagramPacket receivePacket = new DatagramPacket(recieveBuffer,recieveBuffer.length);
            clientSocket.receive(receivePacket);
            String serverMessage = new String(receivePacket.getData());
            System.out.println("\nServer: "+serverMessage);
        }
        clientSocket.close();
    }
    
}
