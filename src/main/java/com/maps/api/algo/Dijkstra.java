package com.maps.api.algo;

import java.util.*;

import com.maps.api.models.Graph;
import com.maps.api.models.Node;

public class Dijkstra {
    public List<Node> dijkstra(Graph graph, Node source, Node destination) {
        // Initialiser les distances et les précédents nœuds
        Map<Node, Integer> distances = new HashMap<>();
        Map<Node, Node> previousNodes = new HashMap<>();
        for (Node node : graph.getNodes()) {
            distances.put(node, Integer.MAX_VALUE);
            previousNodes.put(node, null);
        }
        distances.put(source, 0);

        // File de priorité pour les nœuds à explorer
        PriorityQueue<NodeDistancePair> priorityQueue = new PriorityQueue<>((a, b) -> a.distance - b.distance);
        priorityQueue.add(new NodeDistancePair(source, 0));

        while (!priorityQueue.isEmpty()) {
            NodeDistancePair current = priorityQueue.poll();
            Node currentNode = current.node;
            int currentDistance = current.distance;

            // Ignorer si une meilleure distance est déjà connue
            if (currentDistance > distances.get(currentNode)) {
                continue;
            }

            // Explorer les voisins
            for (Node neighbor : currentNode.getConnectNode()) {
                int weight = calculateDistance(currentNode, neighbor);
                int distanceThroughCurrent = currentDistance + weight;

                if (distanceThroughCurrent < distances.get(neighbor)) {
                    distances.put(neighbor, distanceThroughCurrent);
                    previousNodes.put(neighbor, currentNode);
                    priorityQueue.add(new NodeDistancePair(neighbor, distanceThroughCurrent));
                }
            }
        }

        // Reconstituer le chemin de la source à la destination
        return reconstructPath(previousNodes, source, destination);
    }

    // Calcul de la distance euclidienne
    private int calculateDistance(Node node1, Node node2) {
        int dx = node1.getX() - node2.getX();
        int dy = node1.getY() - node2.getY();
        return (int) Math.sqrt(dx * dx + dy * dy);
    }

    private static class NodeDistancePair {
        Node node;
        int distance;

        NodeDistancePair(Node node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    private List<Node> reconstructPath(Map<Node, Node> previousNodes, Node source, Node destination) {
        List<Node> path = new ArrayList<>();
        for (Node at = destination; at != null; at = previousNodes.get(at)) {
            path.add(at);
        }
        Collections.reverse(path);

        if (path.get(0).equals(source)) {
            return path;
        } else {
            return Collections.emptyList();  // Chemin non trouvé
        }
    }
}
