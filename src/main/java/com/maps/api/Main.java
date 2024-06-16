package com.maps.api;

import com.maps.api.console.Display;
import com.maps.api.models.Node;
import com.maps.api.models.Graph;
import com.maps.api.algo.Dijkstra;

import java.util.List;
import java.util.Map;

public class Main  {

    

    public static void main(String[] args) {
        Graph ville = new Graph("toulouse", 50, 50);
        Node nodeA = new Node(3, 10, "rue1");
        Node nodeC = new Node(15, 20, "rue3");
        ville.addNode(nodeA);
        ville.addNode(new Node(3, 20, "rue2"));
        ville.addNode(nodeC);
        ville.addNode(new Node(15, 10, "rue4"));
        ville.addNode(new Node(25, 2, "rue5"));
        ville.addNode(new Node(25, 40, "rue6"));
        ville.addLiaison("rue1", "rue2");
        ville.addLiaison("rue1", "rue5");
        ville.addLiaison("rue2", "rue6");
        ville.addLiaison("rue3", "rue2");
        ville.addLiaison("rue3", "rue1");
        ville.addLiaison("rue4", "rue1");
        ville.addLiaison("rue4", "rue3");
        ville.addLiaison("rue5", "rue4");
        ville.addLiaison("rue6", "rue3");
        ville.addLiaison("rue6", "rue5");
        Display display = new Display("truc", 50, 50, " ");
        System.out.println(display.getName());
        display.addLine(0, 0, 0, 49, '█');
        display.addLine(0, 0, 49, 0, '█');
        display.addLine(49, 0, 49, 49, '█');
        display.addLine(0, 49, 50, 49, '█');
        for (int i=0; i<ville.getNodes().size(); ++i){
            for (int j=0; j<ville.getNodes().get(i).getConnectNode().size(); ++j){
                display.addLine(ville.getNodes().get(i).getX(), ville.getNodes().get(i).getY(), ville.getNodes().get(i).getConnectNode().get(j).getX(), ville.getNodes().get(i).getConnectNode().get(j).getY(), '+');
            }
        }
        for (int i=0; i<ville.getNodes().size(); ++i){
            display.addPoint(ville.getNodes().get(i).getX(), ville.getNodes().get(i).getY(), '#');
        }
        display.printScreen();
        Dijkstra dijkstra = new Dijkstra();
        List<Node> path = dijkstra.dijkstra(ville, nodeA, nodeC);

        System.out.println("Chemin de A à C:");
        Graph villedijkstra = new Graph("toulousedijkstra", 50, 50);
        for (Node node : path) {
            System.out.println(node.getName() + " (" + node.getX() + ", " + node.getY() + ")");
            villedijkstra.addNode(new Node(node.getX(), node.getY(), node.getName()));
        }
        for (int i=0; i<path.size()-1; ++i) {
            villedijkstra.addLiaison(path.get(i).getName(), path.get(i+1).getName());
        }
        for (int i=0; i<villedijkstra.getNodes().size(); ++i){
            for (int j=0; j<villedijkstra.getNodes().get(i).getConnectNode().size(); ++j){
                display.addLine(villedijkstra.getNodes().get(i).getX(), villedijkstra.getNodes().get(i).getY(), villedijkstra.getNodes().get(i).getConnectNode().get(j).getX(), villedijkstra.getNodes().get(i).getConnectNode().get(j).getY(), '█');
            }
        }
        display.printScreen();
    }
}
