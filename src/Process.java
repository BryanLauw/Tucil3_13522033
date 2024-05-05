public class Process {
    // Atrributes
    private String end;
    private PrioQueueNode q;
    private Path p;
    private Dictionary d;
    private boolean greedy, heuristic;

    /**
     * Make a new process
     * @param begin starting word
     * @param end destination word
     * @param greedy true if the greedy function is used (UCS and A*)
     * @param heuristic true if the heuristic fucntion is used (G-BFS and A*)
     * @param d the dictionary for the words
     */
    public Process(String begin, String end, boolean greedy, boolean heuristic, Dictionary d) {
        this.end = end;
        q = new PrioQueueNode(begin);
        p = new Path();
        this.d = d;
        this.greedy = greedy;
        this.heuristic = heuristic;
    }

    /**
     * Getter for the path (result list)
     * @return the path in the process
     */
    public Path getPath() { return p; }
    /**
     * Getter for
     * @return the queue of nodes
     */
    public PrioQueueNode getQueue() { return q; }

    /**
     * Check the word in the node is the destination word
     * @param n the node to be checked
     * @return true if word in node is destination, false otherwise
     */
    private boolean found(Node n) {
        return end.equals(n.getWord());
    }

    /**
     * Check the path has the most minimun cost
     * @return true if the path's cost is minimum form every node in q
     */
    public boolean isMostMinimum() { return q.isPathMinimal(p); }

    /**
     * Generating child of a node in the head of the queue
     */
    public void generateChild() {
        Node head = q.dequeue();
        if (found(head)) {
            if (!p.isEmptyPath()) {
                p.cleanPath();
            }
            p.makePath(head);
        } else {
            q.listChild(head, end, greedy, heuristic, d);
        }
    }
}
