package exercise.unit_9;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Exercise2 {
    private static class TreeNode {
        String item;
        TreeNode left;
        TreeNode right;

        TreeNode(String str) {
            item = str;
        }
    }

    private static TreeNode root;

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(
                new FileReader("./exercise/resources/unit_7/random_texts.txt"))) {

            String line = "";
            while ((line = reader.readLine()) != null) {
                line = line.trim().toLowerCase();
                if (treeContains(root, line)) {
                    continue;
                }
                treeInsert(line);
            }

            System.out.println("항목의 개수: " + countNodes(root));
            treeList(root);
        } catch (FileNotFoundException e) {
            System.out.println("파일을 찾을 수 없습니다.");
        } catch (IOException e) {
            System.out.println("입출력 오류 발생!");
        }
    }

    /* 문제에서 제공된 소스의 일부 */

    /**
     * Add the item to the binary sort tree to which the global variable
     * "root" refers. (Note that root can't be passed as a parameter to
     * this routine because the value of root might change, and a change
     * in the value of a formal parameter does not change the actual parameter.)
     */
    private static void treeInsert(String newItem) {
        if (root == null) {
            // The tree is empty. Set root to point to a new node containing
            // the new item. This becomes the only node in the tree.
            root = new TreeNode(newItem);
            return;
        }
        TreeNode runner; // Runs down the tree to find a place for newItem.
        runner = root; // Start at the root.
        while (true) {
            if (newItem.compareTo(runner.item) < 0) {
                // Since the new item is less than the item in runner,
                // it belongs in the left subtree of runner. If there
                // is an open space at runner.left, add a new node there.
                // Otherwise, advance runner down one level to the left.
                if (runner.left == null) {
                    runner.left = new TreeNode(newItem);
                    return; // New item has been added to the tree.
                } else
                    runner = runner.left;
            } else {
                // Since the new item is greater than or equal to the item in
                // runner it belongs in the right subtree of runner. If there
                // is an open space at runner.right, add a new node there.
                // Otherwise, advance runner down one level to the right.
                if (runner.right == null) {
                    runner.right = new TreeNode(newItem);
                    return; // New item has been added to the tree.
                } else
                    runner = runner.right;
            }
        } // end while
    } // end treeInsert()

    /**
     * Return true if item is one of the items in the binary
     * sort tree to which root points. Return false if not.
     */
    static boolean treeContains(TreeNode root, String item) {
        if (root == null) {
            // Tree is empty, so it certainly doesn't contain item.
            return false;
        } else if (item.equals(root.item)) {
            // Yes, the item has been found in the root node.
            return true;
        } else if (item.compareTo(root.item) < 0) {
            // If the item occurs, it must be in the left subtree.
            return treeContains(root.left, item);
        } else {
            // If the item occurs, it must be in the right subtree.
            return treeContains(root.right, item);
        }
    } // end treeContains()

    /**
     * Print the items in the tree in inorder, one item to a line.
     * Since the tree is a sort tree, the output will be in increasing order.
     */
    private static void treeList(TreeNode node) {
        if (node != null) {
            treeList(node.left); // Print items in left subtree.
            System.out.println("  " + node.item); // Print item in the node.
            treeList(node.right); // Print items in the right subtree.
        }
    } // end treeList()

    /**
     * Count the nodes in the binary tree.
     * 
     * @param node A pointer to the root of the tree. A null value indicates
     *             an empty tree.
     * @return the number of nodes in the tree to which node points. For an
     *         empty tree, the value is zero.
     */
    private static int countNodes(TreeNode node) {
        if (node == null) {
            // Tree is empty, so it contains no nodes.
            return 0;
        } else {
            // Add up the root node and the nodes in its two subtrees.
            int leftCount = countNodes(node.left);
            int rightCount = countNodes(node.right);
            return 1 + leftCount + rightCount;
        }
    } // end countNodes()
}
