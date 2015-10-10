package Chapter2;

import java.util.HashSet;
import java.util.Random;

/*Various LL functions, from Chapter 2*/
public class LinkedList {

    private class Node {
        private Node next;
        int data;

        Node(int data) {
            this.data = data;
            this.next = null;
        }

    }

    private Node head;

    LinkedList() {
        head = null;
    }

    public void addNode(int data) {
        if (head == null) {
            head =  new Node(data);
        } else {
            Node pntr = head;
            while (pntr.next != null) {
                pntr = pntr.next;
            }
            pntr.next = new Node(data);
        }
    }

    //Delete all of the duplicate nodes in the LL using a buffer Hash
    public void deleteDupesHash() {
        HashSet<Integer> set = new HashSet<>();
        Node pntr = head;
        Node prev = null;
        while (pntr != null) {
            if (set.contains(pntr.data)) {
              prev.next = pntr.next;
            } else {
                set.add(pntr.data);
                prev = pntr;
            }
            pntr = pntr.next;
        }
    }

    //Delete all of the duplicates nodes in the LL without a buffer
    public void deleteDupesNoHash() {
        Node pntr = head;
        Node runner = null;

        while (pntr != null) {
            runner = pntr;
            while (runner.next != null) {
                if(runner.next.data == pntr.data) {
                    runner.next = runner.next.next;
                } else {
                    runner = runner.next;
                }
            }
            pntr = pntr.next;
        }
    }

    //Find the k-th from end element (k = 1 would be the last element)
    private class Index {
        public int val = 0;
    }

    public int findKthElement(int k) {
        if (head == null) {
            return 0;
        }
        Index index = new Index();
        Node temp = kthElement(head, k, index);
        return temp.data;
    }

    private Node kthElement(Node head, int k, Index index) {
        if (head == null) {
            return null;
        }
        Node temp = kthElement(head.next, k, index);
        index.val++;
        if (index.val == k) {
            return head;
        }
        return temp;
    }

    //Find the Kth element iteratively
    public int findKthIterative(int k) {
        Node p1 = head;
        Node p2 = head;

        for (int i = 0; i < k; i++) {
            if (p1 == null) {
                return 0;
            }
            p1 = p1.next;
        }

        while (p1 != null) {
            p1 = p1.next;
            p2 = p2.next;
        }

        return p2.data;
    }

    //Parition nodes around the node with the value of k
    public void Partition(int k) {
        Node beforeHead = null;
        Node beforeTail = null;
        Node afterHead = null;
        Node afterTail = null;

        Node pntr = head;

        while (pntr != null) {
            if (pntr.data < k) {
                if (beforeHead == null) {
                    beforeHead = new Node(pntr.data);
                    beforeTail = beforeHead;
                } else {
                    beforeTail.next = new Node(pntr.data);
                    beforeTail = beforeTail.next;
                }

            } else {
                if (afterHead == null) {
                    afterHead = new Node(pntr.data);
                    afterTail = afterHead;
                } else {
                    afterTail.next = new Node(pntr.data);
                    afterTail = afterTail.next;
                }
            }
            pntr = pntr.next;
        }
        beforeTail.next = afterHead;
        head = beforeHead;

    }

    public String toString() {
        StringBuilder SB = new StringBuilder();
        Node pntr = head;
        while (pntr != null) {
            SB.append(pntr.data + " ");
            pntr = pntr.next;
        }
        return SB.toString();
    }

    public static void main(String[] args) {
        LinkedList LL = new LinkedList();
        Random RN = new Random();
        for (int i = 0; i < 30; i++) {
            int toAdd = RN.nextInt(10);
            LL.addNode(toAdd);
        }
        System.out.println(LL);

        System.out.println(LL.findKthElement(3));
        System.out.println(LL.findKthIterative(3));

        LL.Partition(7);
        System.out.println(LL);


        //LL.deleteDupesHash();
        LL.deleteDupesHash();
        System.out.println(LL);

    }
}
