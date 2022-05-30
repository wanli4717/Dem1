package com.company.aqs;

public class TestP {

    static int st = 0;
    public static void main(String[] args) {
        b();
    }

    public static void b() {
        System.out.println(st++);
        b();
    }
}
