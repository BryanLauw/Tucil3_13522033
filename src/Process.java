public class Process {
    private String end;
    private PrioQueueNode q;
    private Path p;
    private Dictionary d;
    private boolean greedy, heuristic;

    public Process(String begin, String end, boolean greedy, boolean heuristic, Dictionary d) {
        this.end = end.toUpperCase();
        q = new PrioQueueNode(begin.toUpperCase());
        p = new Path();
        this.d = d;
        this.greedy = greedy;
        this.heuristic = heuristic;
    }

    public Path getPath() { return p; }
    public PrioQueueNode getQueue() { return q; }

    public boolean found(Node n) { // ini mungkin private saja
        return end.equals(n.getWord());
    }

    public boolean isMostMinimum() { return q.isPathMinimal(p); }

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
