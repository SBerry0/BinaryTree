import java.util.ArrayList;

/**
 * An Integer Binary Search Tree
 * @author: Sohum Berry
 * @version: 4/3/24
 */

public class BST {
    private BSTNode root;

    public BSTNode getRoot() {
        return this.root;
    }

    public void setRoot(BSTNode root) {
        this.root = root;
    }

    /**
     * Sets up a binary search tree
     * with some default values
     */
    public void setupTestData() {
        this.root = new BSTNode(10);
        this.root.setLeft(new BSTNode(5));
        this.root.setRight(new BSTNode((15)));
        this.root.getLeft().setLeft(new BSTNode(3));
        this.root.getLeft().setRight(new BSTNode(9));
    }

    /**
     * Prints the provided ArrayList of nodes
     * in the form node1-node2-node3
     * @param nodes ArrayList of BSTNodes
     */
    public static void printNodes(ArrayList<BSTNode> nodes) {
        for(int i=0; i<nodes.size()-1; i++) {
            System.out.print(nodes.get(i) + "-");
        }
        System.out.println(nodes.get(nodes.size()-1));
    }

    /**
     * A function that searches for a value in the tree
     * @param val integer value to search for
     * @return true if val is in the tree, false otherwise
     */
    public boolean search(int val) {
        return search(val, root);
    }

    // Do a binary-ish search on the tree for a certain value
    // Return true if the value is in the tree, false if not
    public boolean search(int val, BSTNode node) {
        // If reached the end of the tree, return false
        if (node == null) {
            return false;
        }
        // If found value, return true
        if (val == node.getVal()) {
            return true;
        }
        // Recurse to the left if the value is less than the current node's value
        if (val < node.getVal()) {
            return search(val, node.getLeft());
        }
        // Recurse to the right if the value is greater than the current node's value`
        return search(val, node.getRight());
    }

    /**
     * @return ArrayList of BSTNodes in inorder
     * Adding nodes to ArrayList in the order of Left node, Root, Right node
     */
    public ArrayList<BSTNode> getInorder() {
        ArrayList<BSTNode> out = new ArrayList<BSTNode>();
        getInorder(root, out);
        return out;
    }

    public void getInorder(BSTNode node, ArrayList<BSTNode> out) {
        if (node == null) {
            return;
        }
        // Left...
        getInorder(node.getLeft(), out);
        // Root...
        out.add(node);
        // Right...
        getInorder(node.getRight(), out);
    }

    /**
     * @return ArrayList of BSTNodes in preorder
     * Adding nodes to ArrayList in the order of Root, Left node, Right node
     */
    public ArrayList<BSTNode> getPreorder() {
        ArrayList<BSTNode> out = new ArrayList<BSTNode>();
        getPreorder(root, out);
        return out;
    }

    public void getPreorder(BSTNode node, ArrayList<BSTNode> out) {
        if (node == null) {
            return;
        }
        // Root...
        out.add(node);
        // Left...
        getPreorder(node.getLeft(), out);
        // Right...
        getPreorder(node.getRight(), out);
    }

    /**
     * @return ArrayList of BSTNodes in postorder
     * Adding nodes to ArrayList in the order of Left node, Right node, Root
     */
    public ArrayList<BSTNode> getPostorder() {
        ArrayList<BSTNode> out = new ArrayList<BSTNode>();
        getPostorder(root, out);
        return out;
    }

    public void getPostorder(BSTNode node, ArrayList<BSTNode> out) {
        if (node == null) {
            return;
        }
        // Left...
        getPostorder(node.getLeft(), out);
        // Right...
        getPostorder(node.getRight(), out);
        // Root...
        out.add(node);
    }

    /**
     * Inserts the given integer value to the tree
     * if it does not already exist. Modifies the
     * root instance variable to be the root of the new modified tree.
     * @param val The value ot insert
     */
    public void insert(int val) {
        insert(val, root);
    }

    // Search for the node that the value should be attached to...
    // Then set its correct child to the value and return the child to the parent to connect them.
    public BSTNode insert(int val, BSTNode node) {
        // If you reach an end, create a new node with the value and return it to connect to the rest of the tree
        if (node == null) {
            return new BSTNode(val);
        }
        // return the node that has the goal value
        if (node.getVal() == val) {
            return node;
        }
        // Recurse to the right to search for a value greater than the current node
        if (val > node.getVal()) {
            // Connect the current node to the return value of the recursion
            // If the return value is the inserted node, it gets connected to the rest of the tree
            // If the return value is a regular node, nothing changes
            node.setRight(insert(val, node.getRight()));
            return node;
        }
        // Recurse to the right to search for a value less than the current node
        node.setLeft(insert(val, node.getLeft()));
        return node;
    }

    /**
     * Determines if the current BST is
     * a valid BST.
     * @return true if valid false otherwise
     */
    public boolean isValidBST() {
        // Root is checked from [Integer.MIN_VALUE, Integer.MAX_VALUE]
        // Then next levels would be checked from more restricted bounds based on its root
        // base case would be if you reach null w/ no bounds violated, would have to be with && when calling

        return isValidBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public boolean isValidBST(BSTNode node, int min, int max) {
        if (node == null) {
            return true;
        }
        // If the node's value is out of its allowed bounds, return false
        if (node.getVal() <= min || node.getVal() >= max) {
            return false;
        }
        // All branches must be true so use '&&' when recursing to each side of the tree
        return isValidBST(node.getLeft(), min, node.getVal()) && isValidBST(node.getRight(), node.getVal(), max);
    }

    public static void main(String[] args) {
        // Tree to help you test your code
        BST tree = new BST();
        tree.setupTestData();

        System.out.println("\nSearching for 15 in the tree");
        System.out.println(tree.search(15));

        System.out.println("\nSearching for 22 in the tree");
        System.out.println(tree.search(22));

        System.out.println("\nPreorder traversal of binary tree is");
        ArrayList<BSTNode> sol = tree.getPreorder();
        printNodes(sol);

        System.out.println("\nInorder traversal of binary tree is");
        sol = tree.getInorder();
        printNodes(sol);

        System.out.println("\nPostorder traversal of binary tree is");
        sol = tree.getPostorder();
        printNodes(sol);

        tree.insert(8);
        System.out.println("\nInorder traversal of binary tree is");
        sol = tree.getInorder();
        printNodes(sol);

        boolean isValid = tree.isValidBST();
        System.out.println(isValid);
    }
}
