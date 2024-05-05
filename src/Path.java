import java.util.*;

public class Path {
    // Attributes
    private int cost;
    private List<String> result;

    // Methods
    /**
     * Make a new path
     */
    public Path() {
        cost = 0;
        result = new ArrayList<>();
    }

    /**
     * Getter for the cost of the path
     * @return path's cost
     */
    public int pathCost() {
        return cost;
    }
    /**
     * Getter for the path from the start word to destination word
     * @return path result as List<String>
     */
    public List<String> getResult() {
        return result;
    }

    /**
     * @return true if the path is empty (no path found)
     */
    public boolean isEmptyPath() {
        return result.isEmpty();
    }

    /**
     * Making path from the node to its root
     * @param n the first node (leaf node)
     */
    public void makePath(Node n) {
        cost = n.getCost();
        Node temp = new Node(n);
        while (temp.getParent() != null) {
            result.add(temp.getWord());
            // System.out.println(result); // Janlup hapus
            temp = new Node(temp.getParent());
        }
        result.add(temp.getWord());
        Collections.reverse(result);
    }

    /**
     * Erase the path found
     */
    public void cleanPath() {
        result.clear();
    }
}
