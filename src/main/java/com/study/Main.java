package com.study;

import com.study.connection.ConnectionTest;

public class Main {

    public static void main(String[] args){

        try{
            ConnectionTest t = new ConnectionTest();
            System.out.println(t.getConnection());
        } catch (Exception e){
            System.out.println("Connection err" + e);
        }
    }
}
