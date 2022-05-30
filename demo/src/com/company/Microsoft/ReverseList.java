package com.company.Microsoft;

public class ReverseList {

    public static void main(String[] args) {

    }

    public static Node reverse(Node head) {
        if (null == head) {
            return head;
        }
        Node pre = head;
        Node pNext = pre;

        // 1,2,3

        // find list mid node
        while(pNext != null && pNext.next != null) {
            pre = pre.next;
            pNext = pNext.next.next;
        }

        // pre = 2

        pre = pre.next;
        pNext = new Node(0);

        // reverse list
        while (null != pre) {
            Node tmp = pre;
            pre = pre.next;
            tmp.next = pNext.next;
            pNext.next = tmp;
        }

        // 3.next = null;
        // 0.next = 3;
        // 0 , 3, null;

        // pNext = 0, pNext.next = 3
        if (pNext.next != null) {
            pNext = pNext.next;
        }

        // pNext = 3;

        pre = head;
        Node newHead = new Node(0);
        Node tmp = newHead;
        while (null != pre && pNext != null) {
            Node t1 = pre;
            Node t2 = pNext;

            // t1 = 1;
            // t2 = 3;

            pre = pre.next;
            pNext = pNext.next;

            t1.next = t2;
            t2.next = null;
            tmp.next = t1;
            tmp = t2;
        }

        // tmp = 3, 2
        tmp.next = pre != null ? pre : pNext;

        return newHead.next;
    }

    public static class Node {
        public int val;
        public Node next;

        Node(int val) {
            this(val, null);
        }

        Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }
    }
}




