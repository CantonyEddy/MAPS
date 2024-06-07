package com.maps.api.models;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    private String name;
    private int height;
    private int width;
    private List<Node> nodes;

    public Graph(String name, int height, int width) {
        this.name = name;
        this.height = height;
        this.width = width;
        this.nodes = new ArrayList<>();
    }

    public void init() {
        this.nodes = new ArrayList<>();
    }

    public void addNode(Node node) {
        nodes.add(node);
    }

    public void removeNode(String nodeName) {
        Node nodeToRemove = findNodeByName(nodeName);
        if (nodeToRemove != null) {
            for (Node node : nodes) {
                node.removeNode(nodeToRemove);
            }
            nodes.remove(nodeToRemove);
        }
    }

    public void addLiaison(String nodeName1, String nodeName2) {
        Node node1 = findNodeByName(nodeName1);
        Node node2 = findNodeByName(nodeName2);
        if (node1 != null && node2 != null) {
            node1.addNode(node2);
            node2.addNode(node1);
        }
    }

    public void removeLiaison(String nodeName1, String nodeName2) {
        Node node1 = findNodeByName(nodeName1);
        Node node2 = findNodeByName(nodeName2);
        if (node1 != null && node2 != null) {
            node1.removeNode(node2);
            node2.removeNode(node1);
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

    public List<Node> getNodes() {
        return nodes;
    }
}
