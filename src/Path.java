import java.util.*;

public class Path {
    private int cost;
    private List<String> result;

    public Path() {
        cost = 0;
        result = new ArrayList<>();
    }

    public int pathCost() {
        return cost;
    }
    public List<String> getResult() {
        return result;
    }

    public boolean isEmptyPath() {
        return result.isEmpty();
    }

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

    public void cleanPath() {
        result.clear();
    }
}
