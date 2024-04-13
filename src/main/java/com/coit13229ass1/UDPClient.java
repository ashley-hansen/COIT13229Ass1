package com.coit13229ass1;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class UDPClient {

    public static void main(String[] args) throws IOException, SocketException {
        //initial variables to create socket connection
        int port = 2276;
        Scanner scanner = new Scanner(System.in);
        InetAddress address = InetAddress.getByName("localhost");
        DatagramSocket clientSocket = new DatagramSocket();

//while loop to keep connection unless closed bu the user
        while (true) {
            
            // create bytestreams to send and recieve data
            byte[] sendBuffer = new byte[1024];
            byte[] recieveBuffer = new byte[1024];
            
            //prompt user for selection
            System.out.println("\nPress enter to recive the current member list: type exit to exit the system.");
            //store user response
            String clientMessage = scanner.nextLine();
            //create byte stream
            sendBuffer = clientMessage.getBytes();
            //create datagram packet and send to server for list response
            DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, address, port);
            clientSocket.send(sendPacket);
            
            //close connection on client end if user chooses
            if (clientMessage.equalsIgnoreCase("exit")) {
                System.out.println("Connection ended by client");
                break;
            }
            //create datagram object to recive message from server
            DatagramPacket receivePacket = new DatagramPacket(recieveBuffer, recieveBuffer.length);
            clientSocket.receive(receivePacket);
            //print server message from server formatted
            String serverMessage = new String(receivePacket.getData());
            System.out.printf("%-20s%-50s%s", "Name", "Address", "Phone\n");
            System.out.println("-------------------------------------------------------------------------------");
            System.out.println(serverMessage);
        }
        clientSocket.close();
    }

}
