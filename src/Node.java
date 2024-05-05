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
    public Node(String word) {
        this.word = word;
        this.parent = null;
        depth = 0;
        g = 0;
        h = 0;
    }

    // antara ini yang dihapus
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

    // atau ini
    public Node(String word, Node parent, int g) {
        this.word = word;
        this.parent = parent;
        this.depth = parent.depth + 1;
        this.g = g;
        h = 0;
    }

    public Node(Node n) {
        this.word = n.word;
        this.parent = n.parent;
        this.depth = n.depth;
        this.g = n.g;
        this.h = n.h;
    }

    // Getter
    public String getWord() { return word; }
    public Node getParent() { return parent; }
    public int getDepth() { return depth; }
    public int getG() { return g; }
    public int getH() { return h; }
    public int getCost() { return g+h; }

    // Setter heuristic
    public void changeH(String other) {
        int temp = 0;
        for (int i = 0; i < other.length(); i++) {
            if (word.charAt(i) != other.charAt(i)) {
                temp++;
            }
        }
        h = temp;
    } 

    // Other function
    public boolean existInParent(String other) {
        Node comparator = new Node(this);
        while (comparator.getParent() != null) {
            if (other.equals(comparator.getWord())) {
                return true;
            }
            comparator = new Node(comparator.getParent());
        }
        if (other.equals(comparator.getWord())) {
            return true;
        }
        return false;
    }

    //Janlup hapus
    public String toString() {
        String r = word + String.valueOf(g+h);
        return r;
    }

    // Janlup hapus
    public static void main(String[] args) {
        Node n1 = new Node("A");
        Node n2 = new Node("B", n1);
        Node n3 = new Node("C", n2);
        System.out.println(n3.existInParent("C"));
        System.out.println(n1.word);
        System.out.println(n2.getParent().word);
    }
}