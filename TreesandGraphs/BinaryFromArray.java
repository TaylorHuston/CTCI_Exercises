package TreesAndGraphs;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/*Build a BST from an already sorted array*/
public class BinaryFromArray {

    public static class Node {
        public int data;
        public Node left;
        public Node right;

        Node(int data) {
            this.data = data;
        }

        public static void inOrder(Node head) {
            if (head == null) {
                return;
            }
            inOrder(head.left);
            System.out.print(head.data + " ");
            inOrder(head.right);
        }

        public static void preOrder(Node head) {
            if (head == null) {
                return;
            }
            System.out.print(head.data + " ");
            preOrder(head.left);
            preOrder(head.right);
        }

        public static void postOrder(Node head) {
            if (head == null) {
                return;
            }
            postOrder(head.left);
            postOrder(head.right);
            System.out.print(head.data + " ");
        }

    }

    public static Node buildTree(int[] arr) {
        return buildTree(arr, 0, arr.length - 1);
    }

    private static Node buildTree(int[] arr, int start, int end) {
        if (end < start) {
            return null;
        }
        int mid = (start + end) / 2;
        Node head = new Node(arr[mid]);
        head.left = buildTree(arr, start, mid - 1);
        head.right = buildTree(arr, mid + 1, end);
        return head;
    }

    public static boolean isBalanced(Node head) {
        if (checkHeight(head) == -1) {
            return false;
        } else {
            return true;
        }
    }

    private static int checkHeight(Node head) {
        if (head == null) {
            return 0;
        }

        int leftHeight = checkHeight(head.left);
        if (leftHeight == -1) {
            return -1;
        }

        int rightHeight = checkHeight(head.right);
        if (rightHeight == -1) {
            return -1;
        }

        int heightDiff = leftHeight - rightHeight;
        if (Math.abs(heightDiff) > 1) {
            return -1;
        } else {
            return Math.max(leftHeight, rightHeight) + 1;
        }

    }

    public static boolean checkBST(Node head) {
        return checkBST(head, null, null);
    }

    private static boolean checkBST(Node head, Integer min, Integer max) {
        if (head == null) {
            return true;
        }

        if ((min != null && head.data <= min) || (max != null && head.data > max)) {
            return false;
        }

        if ((checkBST(head.left, min, head.data) == false) || (checkBST(head.right, head.data, max) == false)) {
            return false;
        }

        return true;
    }

    //Return all of the possible arrays that could have created this BST
    public static ArrayList<LinkedList<Integer>> allSequences(Node head) {
        ArrayList<LinkedList<Integer>> result = new ArrayList<>();

        if (head == null) {
            result.add(new LinkedList<Integer>());
            return result;
        }

        LinkedList<Integer> prefix = new LinkedList<>();
        prefix.add(head.data);

        //Recurse on left and right subtrees
        ArrayList<LinkedList<Integer>> leftSeq = allSequences(head.left);
        ArrayList<LinkedList<Integer>> rightSeq = allSequences(head.right);

        //Weave sequences together
        for (LinkedList<Integer> left : leftSeq) {
            for (LinkedList<Integer> right : rightSeq) {
                ArrayList<LinkedList<Integer>> weaved = new ArrayList<>();
                weaveLists(left, right, weaved, prefix);
                result.addAll(weaved);
            }
        }

        return result;
    }

    public static void weaveLists(LinkedList<Integer> first, LinkedList<Integer> second, ArrayList<LinkedList<Integer>> results,
                                  LinkedList<Integer> prefix) {

        if (first.size() == 0 || second.size() == 0) {
            LinkedList<Integer> result = (LinkedList<Integer>) prefix.clone();
            result.addAll(first);
            result.addAll(second);
            results.add(result);
        }

        int headFirst = first.removeFirst();
        prefix.addLast(headFirst);
        weaveLists(first, second, results, prefix);
        prefix.removeLast();
        first.addFirst(headFirst);

        int headSecond = second.removeFirst();
        prefix.addLast(headSecond);
        weaveLists(first, second, results, prefix);
        second.addFirst(headSecond);
    }


    //Check to see if T2 is a subtree of T1
    public static boolean containsTree(Node t1, Node t2) {
        if (t2 == null) {
            return true;
        }

        return subTree(t1, t2);
    }

    private static boolean subTree(Node t1, Node t2) {
        if (t1 == null) {
            return false; //Main tree is empty, no sub tree found
        } else if (t1.data == t2.data && matchTree(t1, t2)) { //The root of t2 matches a node in t1, check ig rest of tree matches
            return true;
        }

        return (subTree(t1.left, t2) || subTree(t1.right, t2));
    }


    private static boolean matchTree(Node t1, Node t2) {
        if (t1 == null && t2 == null) { //Nothing left and all nodes have matched so far
            return true;
        } else if (t1 == null || t2 == null) { //Big tree empty, subtree not found
            return false;
        } else if (t1.data != t2.data) { //Nodes don't match
            return false;
        } else {
            return (matchTree(t1.left, t2.left) && matchTree(t1.right, t2.right));
        }
    }

    //See how many paths in tree equal the given sum

    public static int countPathsWithSum(Node root, int targetSum) {
        return countPathsWithSum(root, targetSum, 0, new HashMap<Integer, Integer>());
    }

    public static int countPathsWithSum(Node node, int targetSum, int runningSum, HashMap<Integer, Integer> pathCount) {
        if (node == null) return 0; // Base case

        runningSum += node.data;

		/* Count paths with sum ending at the current node. */
        int sum = runningSum - targetSum;
        int totalPaths = pathCount.getOrDefault(sum, 0);

		/* If runningSum equals targetSum, then one additional path starts at root. Add in this path.*/
        if (runningSum == targetSum) {
            totalPaths++;
        }

		/* Add runningSum to pathCounts. */
        incrementHashTable(pathCount, runningSum, 1);

		/* Count paths with sum on the left and right. */
        totalPaths += countPathsWithSum(node.left, targetSum, runningSum, pathCount);
        totalPaths += countPathsWithSum(node.right, targetSum, runningSum, pathCount);

        incrementHashTable(pathCount, runningSum, -1); // Remove runningSum
        return totalPaths;
    }

    public static void incrementHashTable(HashMap<Integer, Integer> hashTable, int key, int delta) {
        int newCount = hashTable.getOrDefault(key, 0) + delta;
        if (newCount == 0) { // Remove when zero to reduce space usage
            hashTable.remove(key);
        } else {
            hashTable.put(key, newCount);
        }
    }


    public static void main(String args[]) {
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

        System.out.println(BinaryFromArray.isBalanced(myBST));
        System.out.println(BinaryFromArray.checkBST(myBST));
    }
}
