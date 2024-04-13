package com.coit13229ass1;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;

public class UDPServer {

    public static void main(String[] args) throws SocketException, IOException, ClassNotFoundException {

        int port = 2276;
        DatagramSocket serverSocket = new DatagramSocket(port);
        Scanner scanner = new Scanner(System.in);
        ArrayList<Member> memberList = new ArrayList<Member>();
        while (true) {
            byte[] recieveBuffer = new byte[1024];
            byte[] sendBuffer = new byte[1024];

            DatagramPacket recievePacket = new DatagramPacket(recieveBuffer, recieveBuffer.length);
            serverSocket.receive(recievePacket);
            InetAddress address = recievePacket.getAddress();
            int portNum = recievePacket.getPort();

            String clientMessage = new String(recievePacket.getData());
            System.out.println("\nCLient: " + clientMessage);
            System.out.println("\nServer :");

            FileInputStream sIn = new FileInputStream("memberListObject");
            ObjectInputStream sOut = new ObjectInputStream(sIn);
            memberList = (ArrayList) sOut.readObject();
            StringBuilder sb = new StringBuilder();
            String newLine = "\n";

            for (int i = 0; i < memberList.size(); i++) {
                sb.append(memberList.get(i) + newLine);
            }
            String str = sb.toString();

            System.out.println(memberList);

            sendBuffer = str.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, address, portNum);
            serverSocket.send(sendPacket);
            if (clientMessage.equalsIgnoreCase("bye")) {
                System.out.println("Connection ended by server");
                break;
            }
        }

        serverSocket.close();

    }
}
