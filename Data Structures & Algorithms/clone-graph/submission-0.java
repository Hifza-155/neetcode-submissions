/*
Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/
class Solution {
    Map<Node, Node> copyMap = new HashMap<>();

    public Node cloneGraph(Node node) {
        if (node == null) return null;

        dfs(node);
        
        return copyMap.get(node);
    }

    private void dfs(Node node) {
        if (copyMap.containsKey(node)) return;

        Node copy = new Node(node.val);
        copyMap.put(node, copy);

        for (Node neighbor : node.neighbors) {
            dfs(neighbor); // call dfs on neighbor to save a copy of neighbor
            copy.neighbors.add(copyMap.get(neighbor)); // add copy of neighbor
        }
    }
}