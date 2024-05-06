public class Node {
    // Attributes
    private String word;
    private Node parent;
    private int depth;
    // Greedy value
    private int g;
    // Heuristic value
    private int h;

    // Methods
    // Constructors
    /**
     * Make a new Node
     * @param word the word in the node
     */
    public Node(String word) {
        this.word = word;
        this.parent = null;
        depth = 0;
        g = 0;
        h = 0;
    }

    /**
     * Make a new Node
     * @param word the word in the node
     * @param parent the Node it generated from
     */
    public Node(String word, Node parent) {
        this.word = word;
        this.parent = parent;
        if (parent == null) {
            depth = 0;
        } else {
            depth = parent.depth+1;
        }
        g = 0;
        h = 0;
    }

    /**
     * Make a new Node
     * @param word the word in the node
     * @param parent the Node it generated from
     * @param g the greedy value
     */
    public Node(String word, Node parent, int g) {
        this.word = word;
        this.parent = parent;
        this.depth = parent.depth + 1;
        this.g = g;
        h = 0;
    }

    /**
     * Make a new Node
     * @param n a Node that will be copied
     */
    public Node(Node n) {
        this.word = n.word;
        this.parent = n.parent;
        this.depth = n.depth;
        this.g = n.g;
        this.h = n.h;
    }

    // Getter
    /**
     * Getter for the word
     * @return word in the nod
     */
    public String getWord() { return word; }
    /**
     * Getter for parent Node
     * @return reference to the parent
     */
    public Node getParent() { return parent; }
    /**
     * Getter for depth
     * @return the depth of the node
     */
    public int getDepth() { return depth; }
    /**
     * Getter for g
     * @return greedy value of the node
     */
    public int getG() { return g; }
    /**
     * Getter for total g and h
     * @return the cost of the node
     */
    public int getCost() { return g+h; }

    /**
     * Finding the heuristic value of the word in this node
     * The heuristic value is the amount of different alphabeth with other
     * @param other destination string
     */
    public void changeH(String other) {
        int temp = 0;
        for (int i = 0; i < other.length(); i++) {
            if (word.charAt(i) != other.charAt(i)) {
                temp++;
            }
        }
        h = temp;
    }
}