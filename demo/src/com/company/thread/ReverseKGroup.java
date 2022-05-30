package com.company.thread;

public class ReverseKGroup {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        ListNode head = new ListNode(0);
        ListNode next = head;
        for (int num : arr) {
            ListNode node = new ListNode(num);
            next.next = node;
            next = next.next;
        }

        head = reverse(head.next, 2);

        while(head != null) {
            System.out.print(head.val + "->");
            head = head.next;
        }
    }

    public static ListNode reverse(ListNode head, int k) {
        ListNode top = new ListNode(0);
        ListNode tmp = top;
        ListNode next = top;

        // System.out.println(tmp.next == null);

        int i = 0;
        while(head != null) {
            ListNode node = head;
            head = head.next;
            node.next = tmp.next;
            tmp.next = node;
            if (i == 0) {
                next = tmp.next;
            }
            i++;
            if (i == k) {
                i = 0;
                tmp = next;
            }
        }

        return top.next;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
