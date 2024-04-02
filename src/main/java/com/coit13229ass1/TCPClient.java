package com.coit13229ass1;

import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TCPClient {

    public static void main(String[] args) {
        Socket s = null;
        boolean  moreMembers = true;
        String name;
        String address;
        String phone;
        while(moreMembers){
        try {

            int serverPort = 8000;
            s = new Socket("localhost", serverPort);
            ObjectInputStream in = null;
            ObjectOutputStream out = null;

            out = new ObjectOutputStream(s.getOutputStream());
            in = new ObjectInputStream(s.getInputStream());
            
            
            
            Member member = new Member();
            member.setName(new String("Mark Ciampa"));
            member.setAdress(new String("Fudge Ride"));
            member.setPhone(new String("123456"));
            out.writeObject(member);
            System.out.println("Member Sent");
            
            Member recievedMember = (Member)in.readObject();
            System.out.println(recievedMember);
        } catch (IOException ex) {
            Logger.getLogger(TCPClient.class.getName()).log(Level.SEVERE, null, ex);
        }catch(ClassNotFoundException e){e.printStackTrace();
        }
        }

    }
}
