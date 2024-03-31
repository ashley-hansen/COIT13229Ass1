package com.coit13229ass1;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class TCPClient {

    public static void main(String[] args) {

    }// end of main method

    public static void dataEntry() {
        boolean isNext = true;

        while (isNext) {

            String name;
            String address;
            String phone;

            Scanner input = new Scanner(System.in);

            System.out.println("Enter name for member number:");
            name = input.nextLine();
            System.out.println("Enter address for member number:");
            address = input.nextLine();
            System.out.println("Enter phone for member number:");
            phone = input.nextLine();
            System.out.println("would you like to enter details for another member?: y or n?");
            String cont = input.nextLine();
            System.out.println(cont);

            if (cont.equals("n")) {
                break;
            }

        }
    }
}//end of class
