package com.company.mq;

public class LRU {


    public class Node{
        public int key, value;
        public Node next, prev;

        public Node(int key, int value)  {
            this.key = key;
            this.value = value;
        }
    }

    public class DoubleList {

        private Node head, tail;
        private int size;

        public void addFirst(Node node) {
            if (null == head) {
                head = tail = node;
            } else {
                Node n = head;
                n.prev = node;
                node.next = n;
                head = node;
            }
            size ++;
        }

        public void remove(Node node) {
            if (head == node && tail == node) {
                head = null;
                tail = null;
            } else if (tail == node){
                node.prev.next = null;
                tail = node.prev;
            } else if (head == node) {
                node.next.prev = null;
                head = node.next;
            } else {
                node.next.prev = node.prev;
                node.prev.next = node.next;
                node = null;
            }
            size--;
        }

        public void removeLast() {
            Node node = tail;
            remove(node);
        }
    }
}



