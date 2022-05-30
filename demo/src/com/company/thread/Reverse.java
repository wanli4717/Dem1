package com.company.thread;


import java.util.Deque;
import java.util.LinkedList;

public class Reverse {

    public static String reverse(String str) {
        if (null == str) {
            return null;
        }
        if (str.length() <= 1) {
            return str;
        }

        StringBuilder stringBuilder = new StringBuilder();
        Deque<Integer> stack = new LinkedList<>();
        int[] pairs = new int[str.length()];
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') {
                stack.push(i);
            } else if (str.charAt(i) == ')') {
                int index = stack.pop();
                pairs[index] = i;
                pairs[i] = index;
            }
        }
        int i = 0;
        int j = 1;
        while (i < str.length()) {
            if (str.charAt(i) == '(' || str.charAt(i) == ')') {
                j = -j;
                i = pairs[i];
            } else {
                stringBuilder.append(str.charAt(i));
            }
            i += j;

        }

        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        String str = "(ab(cd))";

        System.out.println(reverse(str));
    }
}
