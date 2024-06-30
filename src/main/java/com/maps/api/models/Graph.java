package com.maps.api.models;

import java.util.ArrayList;
import java.util.List;
import com.maps.api.models.Node;

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

    public void addNode(Node node) {
        this.nodes.add(node);
    }

    public void removeNode(String nodeName) {
        for (int i=0; i<this.nodes.size(); ++i){
            if (this.nodes.get(i).getName().equals(nodeName)){
                this.nodes.remove(i);
            }
        }
    }

    public void addLiaison(String nodeName1, String nodeName2) {
        Node node1 = findNodeByName(nodeName1);
        Node node2 = findNodeByName(nodeName2);
        if (node1 != null && node2 != null) {
            node1.addNode(node2);
        }
    }

    public void removeLiaison(String nodeName1, String nodeName2) {
        Node node1 = findNodeByName(nodeName1);
        if (node1 != null) {
            node1.removeNode(nodeName2);
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

    public Node getNodeByName(String name) {
        for (Node node : this.nodes){
            if (node.getName().equals(name)){
                return node;
            }
        }
        return null;
    }
}
