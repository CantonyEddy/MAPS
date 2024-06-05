package com.maps.api.models;

public class Graph {
    private String name;
    private int height;
    private int width;
    private ArrayList<Node> Nodes;

    public Graph {
        this.name = name;
        this.height = height;
        this.width = width;
        this.nodes = new ArrayList<>();
    }

    public void addNode(Node) {
        nodes.add(Node);
    }

    public void remouveNode(str) {
        Node nodeToRemove = findNodeByName(str);
        if (nodeToRemove != null) {
            for (Node : nodes) {
                Node.removeNode(nodeToRemove);
            }
            nodes.remove(nodeToRemove);
        }
    }

    public void addLiaison(str1, str2) {
        Node node1 = findNodeByName(str1);
        Node node2 = findNodeByName(str2);
        if (node1 != null && node2 != null) {
            node1.addNode(node2);
            node2.addNode(node1);
        }
    }

    public remouveLiaison(str1, str2) {
        Node node1 = findNodeByName(str1);
        Node node2 = findNodeByName(str2);
        if (node1 != null && node2 != null) {
            node1.removeNode(node2);
            node2.removeNode(node1);  // Si la liaison est bidirectionnelle
        }
    }

    private Node findNodeByName(String nodeName) {
        for (Node node : nodes) {
            if (node.getName().equals(nodeName)) {
                return node;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public ArrayList<Node> getNodes() {
        return nodes;
    }
}

