package com.coit13229ass1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class TCPServer {

    public static void main(String[] args) throws FileNotFoundException {

    }

    public static void readWrite(String name, String address, String phone) throws FileNotFoundException {

        ArrayList<Member> memberArray = new ArrayList<Member>();
        ArrayList<Member> newArray = new ArrayList<Member>();
        memberArray.add(new Member("Jerry Brown", "123 Thorn st", "123 456"));
        memberArray.add(new Member("Reece the Beast", "555 Egg st", "123 987"));
        memberArray.add(new Member("Ollie Flannagan", "987 Rose Ln", "456 487"));

        File memberFile = new File("memberlist.txt");
        PrintStream print = new PrintStream(memberFile);
        Scanner fileScanner = new Scanner(memberFile);
        for (int i = 0; i <= memberArray.size() - 1; i++) {
            print.println(memberArray.get(i).getName());
            print.println(memberArray.get(i).getAdress());
            print.println(memberArray.get(i).getPhone());
        }

        while (fileScanner.hasNext()) {
            name = fileScanner.nextLine();
            address = fileScanner.nextLine();
            phone = fileScanner.nextLine();

            newArray.add(new Member(name, address, phone));

        }
    }

}
