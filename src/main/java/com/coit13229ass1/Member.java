
package com.coit13229ass1;

import java.io.Serializable;


public class Member implements Serializable{
    private String name;
    private String adress;
    private String phone;

    public Member() {
    }

    public Member(String name, String adress, String phone) {
        this.name = name;
        this.adress = adress;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return String.format("%-20s%-50s%s",name, adress,phone);
    }
    
    
}
