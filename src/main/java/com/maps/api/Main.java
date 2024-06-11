package com.maps.api;

import com.maps.api.console.Display;
import com.maps.api.console.Display.*;
import com.maps.api.models.Node;

public class Main {
    public static void main(String[] args) {
        Node node1 = new Node(1, 2, "Node1");
        Node node2 = new Node(3, 4, "Node2");
        Node node3 = new Node(5, 6, "Node3");

        node1.addNode(node2);
        node1.addNode(node3);

        System.out.println("Node 1 name: " + node1.getName()); // Affiche Node1
        System.out.println("Node 1 connected nodes: " + node1.getConnectNode().size()); // Affiche 2
        System.out.println("Node 1 first connected node name: " + node1.getConnectNode().get(0).getName()); // Affiche Node2
        System.out.println("Node 1 second connected node name: " + node1.getConnectNode().get(1).getName()); // Affiche Node3

        node1.removeNode(node2);
        System.out.println("Node 1 connected nodes after removal: " + node1.getConnectNode().size()); // Affiche 1
    }
}
