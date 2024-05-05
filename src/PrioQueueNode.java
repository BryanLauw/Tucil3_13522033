import java.util.*;

public class PrioQueueNode {
    private List<Node> queueOfWords;
    private Map<String, Integer> foundWord;
    private int generateAmount;

    public PrioQueueNode() {
        queueOfWords = new ArrayList<Node>();
        foundWord = new HashMap<String, Integer>();
        generateAmount = 0;
    }
    
    public PrioQueueNode(String begin) {
        queueOfWords = new ArrayList<Node>();
        foundWord = new HashMap<String, Integer>();
        Node n = new Node(begin, null);
        enqueue(n);
    }

    public List<Node> getQueueOfWords() { return queueOfWords; }
    public int getGeneratedNode() { return generateAmount; }

    public void enqueue(Node n) {
        int cost = n.getCost();
        int idx = 0;
        
        while (idx < queueOfWords.size() && cost >= queueOfWords.get(idx).getCost()) {
            idx++;
        }
        queueOfWords.add(idx, n);
        foundWord.put(n.getWord(), n.getDepth());
        generateAmount++;
    }

    public Node dequeue() {
        Node ret = queueOfWords.get(0);
        queueOfWords.remove(0);
        return ret;
    }

    public boolean isEmptyQueue() {
        return queueOfWords.isEmpty();
    }

    public boolean isPathMinimal(Path p) {
        if (isEmptyQueue()) { return true; }
        return (p.pathCost()) <= (queueOfWords.get(0).getCost());
    }

    public boolean isWordChecked(String s, int depth) {
        if (foundWord.get(s) != null) {
            if (foundWord.get(s) >= depth) {
                foundWord.remove(s);
                return false;
            }
            return true;
        }
        return false;
    }

    public void listChild(Node now, String n, boolean greedy, boolean heuristic, Dictionary d) {
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
                //(d.isWordValid(temp)) && // Janlup hapus
                if ((d.isWordValid(temp)) && !(now.existInParent(temp)) && !(isWordChecked(temp, currentDepth))) {
                    Node tail = new Node(temp, now, g);
                    if (heuristic) { tail.changeH(n); }
                    enqueue(tail);
                }
            }
        }
    }
}
