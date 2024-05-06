import java.util.*;

public class PrioQueueNode {
    // Attributes
    private List<Node> queueOfWords;
    private Map<String, Integer> foundWord; /* key = word, value = depth found */
    private int generateAmount;

    //Methods    
    /**
     * Make a new Priority Queue
     * @param begin the starting word
     */
    public PrioQueueNode(String begin) {
        queueOfWords = new ArrayList<Node>();
        foundWord = new HashMap<String, Integer>();
        foundWord.put(begin, 0);
        Node n = new Node(begin, null);
        enqueue(n);
    }

    /**
     * Getter for the queue
     * @return the queue it has as List<Node>
     */
    public List<Node> getQueueOfWords() { return queueOfWords; }
    /**
     * Getter for generateAmount
     * @return the amount of node that the process has made
     */
    public int getGeneratedNode() { return generateAmount; }

    /**
     * Insert a node to a queue. The lower thes cost of the node, the higher the priority
     * @param n the new node to be inserted
     */
    private void enqueue(Node n) {
        int cost = n.getCost();
        int idx = 0;
        
        while (idx < queueOfWords.size() && cost >= queueOfWords.get(idx).getCost()) {
            idx++;
        }
        queueOfWords.add(idx, n);
        foundWord.put(n.getWord(), n.getDepth());
    }

    /**
     * Erasing the head of the queue
     * @return The first element in the queue
     */
    public Node dequeue() {
        Node ret = queueOfWords.get(0);
        queueOfWords.remove(0);
        return ret;
    }

    /**
     * @return true if the queue is empty
     */
    public boolean isEmptyQueue() {
        return queueOfWords.isEmpty();
    }

    /**
     * Check the path has the most minimum cost
     * @param p The path to be checked
     * @return true if cost of queue's head >= path's cost
     */
    public boolean isPathMinimal(Path p) {
        if (isEmptyQueue()) { return true; }
        return (p.pathCost()) <= (queueOfWords.get(0).getCost());
    }

    /**
     * Check whether the word is already in the map with lower value
     * @param s the word to be checked
     * @param depth the depth of the word
     * @return true if word doesn't exist in the map or exists with value <= depth
     */
    private boolean isWordChecked(String s, int depth) {
        if (foundWord.get(s) != null) {
            if (foundWord.get(s) >= depth) {
                foundWord.remove(s);
                return false;
            }
            return true;
        }
        return false;
    }

    /**
     * List all children of the node and enqueue them to the queue
     * The word will be enqueued if word is not found in parent,
     * isWordChecked returns false, dan word is in dictionary
     * @param now current node
     * @param n the destination word
     * @param greedy true if greedy function is needed
     * @param heuristic true if heuristic function is needed
     * @param d the dictionary
     */
    public void listChild(Node now, String n, boolean greedy, boolean heuristic, Dictionary d) {
        generateAmount++;
        String x = now.getWord();
        int currentDepth = now.getDepth() + 1;
        int g = 0;
        if (greedy) { g = now.getG()+1; }
        for (int i = 0; i < x.length(); i++) {
            for (int j = 65; j <= 90; j++) {
                char c = (char)j;
                String temp = (x.substring(0, i) + c);
                if (i != x.length()-1) {
                    temp = temp + x.substring(i+1);
                }
                if ((d.isWordValid(temp)) && !(isWordChecked(temp, currentDepth))) {
                    Node tail = new Node(temp, now, g);
                    if (heuristic) { tail.changeH(n); }
                    enqueue(tail);
                }
            }
        }
    }
}
