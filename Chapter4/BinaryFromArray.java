package Chapter4;


/*Build a BST from an already sorted array*/
public class BinaryFromArray {

    public static class Node {
        public int id;
        public Node left;
        public Node right;

        Node(int id) {
           this.id = id;
        }

        public static void inOrder(Node head) {
            if (head == null) {
                return;
            }
            inOrder(head.left);
            System.out.print(head.id + " ");
            inOrder(head.right);
        }

        public static void preOrder(Node head) {
            if (head == null) {
                return;
            }
            System.out.print(head.id + " ");
            preOrder(head.left);
            preOrder(head.right);
        }

        public static void postOrder(Node head) {
            if (head == null) {
                return;
            }
            postOrder(head.left);
            postOrder(head.right);
            System.out.print(head.id + " ");
        }

    }

    public static Node buildTree(int[] arr) {
        return buildTree(arr, 0, arr.length-1);
    }

    private static Node buildTree(int[] arr, int start, int end) {
        if (end < start) {
            return null;
        }
        int mid  = (start+end)/2;
        Node head = new Node(arr[mid]);
        head.left = buildTree(arr, start, mid-1);
        head.right = buildTree(arr, mid+1, end);
        return head;
    }

    public static void main (String args[]) {
        int[] sorted = new int[10];
        for (int i = 0; i < 10; i++) {
            sorted[i] = i;
        }
        Node myBST = buildTree(sorted);

        Node.inOrder(myBST);
        System.out.println();

        Node.preOrder(myBST);
        System.out.println();

        Node.postOrder(myBST);
        System.out.println();
    }
}
