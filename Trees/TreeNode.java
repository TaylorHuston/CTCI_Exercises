package Trees;

/*Binary Tree Node*/
public class TreeNode {
    public int data;
    public TreeNode left;
    public TreeNode right;

    TreeNode(int data) {
        this.data = data;
    }

    public static void inOrder(TreeNode head) {
        if (head == null) {
            return;
        }
        inOrder(head.left);
        System.out.print(head.data + " ");
        inOrder(head.right);
    }

    public static void preOrder(TreeNode head) {
        if (head == null) {
            return;
        }
        System.out.print(head.data + " ");
        preOrder(head.left);
        preOrder(head.right);
    }

    public static void postOrder(TreeNode head) {
        if (head == null) {
            return;
        }
        postOrder(head.left);
        postOrder(head.right);
        System.out.print(head.data + " ");
    }

}
