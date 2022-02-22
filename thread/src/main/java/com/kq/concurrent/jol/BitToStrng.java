package com.kq.concurrent.jol;

public class BitToStrng {

    private String name;
    private int age;


    public BitToStrng(){

    }

    public BitToStrng(String name){
        this.name = name;
    }


    public static void main(String[] args) {
        int num = 1782113663;

        System.out.println(Integer.toBinaryString(num));

    }

}
