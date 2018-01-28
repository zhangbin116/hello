package com.company;

public class Main {

    public static void main(String[] args) {
        // write your code here

        System.out.println("hello world....");
        int temp = 0;
        int temp1 = 1;
        add(temp, temp1);

    }

    public static int add(int a, int b) {
        return a + b;
    }

    public static int minus(int a, int b) {
        if (a > b) {
            return a - b;
        }
        return b - a;

    }
}
