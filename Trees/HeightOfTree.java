package Trees;

/*Find the height of a binary tree*/
public class HeightOfTree {
    public static int height(BinaryFromArray.Node head) {
        if (head == null) {
            return 0;
        } else {
            return 1+Math.max(height(head.left), height(head.right));
        }
    }
}
