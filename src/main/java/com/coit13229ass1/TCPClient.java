package com.coit13229ass1;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class TCPClient {

    public static void main(String[] args) {
        dataEntry();
    }// end of main method

    public static void dataEntry() {
        boolean isNext = true;

        while (isNext) {

            try {
                InputStreamReader is = new InputStreamReader(System.in);
                BufferedReader input = new BufferedReader(is);

                System.out.println("Enter name for member number:");
                String name = input.readLine();
                System.out.println("Enter address for member number:");
                String address = input.readLine();
                System.out.println("Enter phone for member number:");
                String phone = input.readLine();
                System.out.println("would you like to enter details for another member?: Type 'n' to exit or anyother key to continue");
                String cont = input.readLine();
                cont = cont.toLowerCase();
                System.out.println(cont);
                if (cont.equals("n")) {
                isNext = false;
            }

            
            }catch(IOException e){
                e.printStackTrace();
            }

        }
    }
}//end of class
