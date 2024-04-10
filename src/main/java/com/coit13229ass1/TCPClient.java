package com.coit13229ass1;

import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TCPClient {

    public static void main(String[] args) {
        Socket s = null;
        boolean moreMembers = true;
        String name;
        String address;
        String phone;
        String response;
        Scanner input = new Scanner(System.in);

        try {
            int serverPort = 1176;
            while (moreMembers) {
            s = new Socket("localhost", serverPort);
            
                ObjectInputStream in = null;
                ObjectOutputStream out = null;

                out = new ObjectOutputStream(s.getOutputStream());
                in = new ObjectInputStream(s.getInputStream());

                Member member = new Member();
                System.out.println("Enter member name:");
                name = input.nextLine();
                member.setName(new String(name));

                System.out.println("Enter member address:");
                address = input.nextLine();
                member.setAdress(new String(address));

                System.out.println("Enter member phone:");
                phone = input.nextLine();
                member.setPhone(new String(phone));

                out.writeObject(member);
                System.out.println("Member Sent");

                Member recievedMember = (Member) in.readObject();
                System.out.println("Recieved Member " + recievedMember.getName());

                System.out.println("Would you like to enter another member? Type 'n' for no otherwise press any key to continue");
                response = input.nextLine().toLowerCase();

                if (response.equals("n")) {
                    System.out.println("Thank you for using the member management system.");
                    moreMembers = false;
                }
                
                
            }
        } catch (IOException ex) {
            Logger.getLogger(TCPClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
