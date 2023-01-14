import java.util.Queue;
import java.util.Stack;

public class BinaryTree<V extends Comparable<V>> {
    private Node<V> root;

    private int size;

    public BinaryTree(Node<V> root) {
        this.root = root;
    }

    public Node<V> getRoot() {
        return this.root;
    }
    public int getSize() {
        return this.size;
    }

    public void printInorder() {
        printInOrderHelper(root);
    }
    private void printInOrderHelper(Node<V> node) {

        if ( node == null ){
            return;
        }
        printInOrderHelper(node.getLeft());
        System.out.print(node.getValue() + " ");
        this.size++;
        printInOrderHelper(node.getRight());


        // TODO: Fill in definition
    }

    public void printPreorder(){
        printPreorderHelper(root);
    }
    private void printPreorderHelper(Node<V> node) {

        if ( node == null ){
            return;
        }
        System.out.print(node.getValue() + " ");
        this.size++;
        printPreorderHelper(node.getLeft());
        printPreorderHelper(node.getRight());
        // TODO: Fill in definition
    }

    public void printPostorder() {
        printPostorderHelper(root);
    }
    private void printPostorderHelper(Node<V> node) {

        if ( node == null ){
            return;
        }
        printPostorderHelper(node.getLeft());
        printPostorderHelper(node.getRight());
        System.out.print(node.getValue() + " ");
        this.size++;
        // TODO: Fill in definition
    }

    private void getInOrder(Node<V> node, Stack<V> stack) {

        if ( node == null ){
            return;
        }
        printInOrderHelper(node.getLeft());
        stack.push(node.getValue());
        this.size++;
        printInOrderHelper(node.getRight());


        // TODO: Fill in definition
    }

    public V[] flatten() {

        Stack<V> treeStack = new Stack<>();
        getInOrder(this.root, treeStack);

        V[] arr = (V[]) new Comparable[treeStack.size()];

        int i = 0;
        while ( !treeStack.isEmpty() )
        {
            arr[i] = treeStack.pop();
            i++;
        }

        sort(arr);


        // TODO: Fill in definition
        return arr;
    }


    // bubble sort
    // useful for flatten
    public void sort(Comparable[] a) {
        int i, j;
        Comparable temp;
        boolean swapped = true;
        for (i = 0; i < a.length && swapped == true; i++) {
            swapped = false;
            for (j = 1; j < a.length - i; j++) {
                if (a[j].compareTo(a[j-1]) < 0) {
                    swapped = true;
                    temp = a[j];
                    a[j] = a[j-1];
                    a[j-1] = temp;
                }
            }
        }
    }

    public void invert() {
        invertHelper(root);
    }

    public void invertHelper(Node<V> node) {

        // base case: tree is empty
        if (root == null) {
            return;
        }

        Stack<Node<V>> s =  new Stack<>();
        s.push(root);

        // loop stack
        while (!s.empty())
        {
            // pop top node
            Node<V> curr = s.peek();
            s.pop();

            // swap left and right
            Node<V> temp = curr.getLeft();
            curr.setLeft(curr.getRight());
            curr.setRight(temp);

            if (curr.getRight() != null) {
                s.push(curr.getRight());
            }

            if (curr.getLeft() != null) {
                s.push(curr.getLeft());
            }
        }
        // TODO: Fill in definition

    }

    public boolean containsSubtree(BinaryTree<V> other) {
        // TODO: Fill in definition
        return false;
    }
    

    // Main contains tests for each milestone.
    // Do not modify existing tests.
    // Un-comment tests by removing '/*' and '*/' as you move through the milestones.
    public static void main (String args[]) {
        // Tree given for testing on
        BinaryTree<Integer> p1Tree = new BinaryTree<Integer>(new Node<Integer>(1,
                new Node<Integer>(2,
                        new Node<Integer>(4, null, null),
                        new Node<Integer>(5, null, null)),
                new Node<Integer>(3, null, null)));

        // Milestone 1 (Traversing)
        System.out.println("--- Milestone 1 ---");
        System.out.print("Expected: 4 2 5 1 3" + System.lineSeparator() + "Actual: ");
        p1Tree.printInorder();
        System.out.println(System.lineSeparator());
        System.out.print("Expected: 1 2 4 5 3" + System.lineSeparator() + "Actual: ");
        p1Tree.printPreorder();
        System.out.println(System.lineSeparator());
        System.out.print("Expected: 4 5 2 3 1" + System.lineSeparator() + "Actual: ");
        p1Tree.printPostorder();
        System.out.println();

        // Milestone 2 (flatten) -- expected output: 1 2 3 4 5

        System.out.println(System.lineSeparator() + "--- Milestone 2 ---");
        System.out.print("Expected: 1 2 3 4 5" + System.lineSeparator() + "Actual: ");

        Comparable[] array_representation = p1Tree.flatten();
        p1Tree.sort(array_representation);
        for (int i = 0; i < array_representation.length; i++) {
            System.out.print(array_representation[i] + " ");
        }
        System.out.println();


        // Milestone 3 (invert)

        System.out.println(System.lineSeparator() + "--- Milestone 3 ---");

        p1Tree.invert();

        System.out.print("Expected: 3 1 5 2 4" + System.lineSeparator() + "Actual: ");
        p1Tree.printInorder();
        System.out.println(System.lineSeparator());
        System.out.print("Expected: 1 3 2 5 4" + System.lineSeparator() + "Actual: ");
        p1Tree.printPreorder();
        System.out.println(System.lineSeparator());
        System.out.print("Expected: 3 5 4 2 1" + System.lineSeparator() + "Actual: ");
        p1Tree.printPostorder();
        System.out.println();


        // Milestone 4 (containsSubtree)
        /*
        System.out.println(System.lineSeparator() + "--- Milestone 4 ---");

        p1Tree = new BinaryTree<Integer>(new Node<Integer>(1,
                new Node<Integer>(2,
                        new Node<Integer>(4, null, null),
                        new Node<Integer>(5, null, null)),
                new Node<Integer>(3, null, null)));
        BinaryTree<Integer> p2Tree = new BinaryTree<Integer>(new Node<Integer>(2,
                new Node<Integer>(4, null, null),
                new Node<Integer>(5, null, null)));
        BinaryTree<Integer> p3Tree = new BinaryTree<Integer>(new Node<Integer>(1,
                new Node<Integer>(2, null, null),
                new Node<Integer>(3, null, null)));
        BinaryTree<Integer> p4Tree = null;

        System.out.print("Expected: true" + System.lineSeparator() + "Actual: ");
        System.out.println(p1Tree.containsSubtree(p2Tree));
        System.out.println();

        System.out.print("Expected: false" + System.lineSeparator() + "Actual: ");
        System.out.println(p1Tree.containsSubtree(p3Tree));
        System.out.println();

        System.out.print("Expected: true" + System.lineSeparator() + "Actual: ");
        System.out.println(p1Tree.containsSubtree(p4Tree));
        */

    }
}
