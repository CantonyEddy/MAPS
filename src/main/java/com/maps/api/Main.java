package com.maps.api;

import com.maps.api.console.Display;
import com.maps.api.models.Node;
import com.maps.api.models.Graph;
import com.maps.api.algo.Dijkstra;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main  {

    // Reset
    public static final String RESET = "\033[0m";  // Text Reset

    // Regular Colors
    public static final String BLACK = "\033[0;30m";   // BLACK
    public static final String RED = "\033[0;31m";     // RED
    public static final String GREEN = "\033[0;32m";   // GREEN
    public static final String YELLOW = "\033[0;33m";  // YELLOW
    public static final String BLUE = "\033[0;34m";    // BLUE
    public static final String PURPLE = "\033[0;35m";  // PURPLE
    public static final String CYAN = "\033[0;36m";    // CYAN
    public static final String WHITE = "\033[0;37m";   // WHITE    

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
        boolean end = true;
        while (end) {
            clearConsole();
            printGraph(ville, display, '+');
            display.printScreen();
            Scanner scanner = new Scanner(System.in);

            // Demander à l'utilisateur de saisir un nombre entier
            System.out.print(GREEN +"Veuillez saisir un choix: \n" + RESET);
            System.out.print("("+ YELLOW +"1" + RESET + ") Ajouter un point.\n");
            System.out.print("("+ YELLOW +"2" + RESET + ") Ajouter une route : A -> B.\n");
            System.out.print("("+ YELLOW +"3" + RESET + ") Trouver un chemin : A -> B.\n");
            while (!scanner.hasNextInt()) {
                System.out.println("Ce n'est pas un nombre entier. Veuillez réessayer.");
                scanner.next(); // Consomme l'entrée incorrecte
            }
            int number = scanner.nextInt();
            scanner.nextLine();
            switch (number) {
                case 1:
                    System.out.print("Veuillez saisir les coordonnes et le nom du point séparés par des espaces (ex : X Y NAME): ");
                    String input = scanner.nextLine();  // Lire toute la ligne
                    String[] parts = input.split(" ");  // Diviser la ligne en parties
            
                    // Convertir les parties en entiers
                    if (isNumeric(parts[0]) && isNumeric(parts[1])) {
                        int number1 = Integer.parseInt(parts[0]);
                        int number2 = Integer.parseInt(parts[1]);
                        String name = parts[2];
                
                        // Afficher les nombres saisis
                        ville.addNode(new Node(number1, number2, name));
                    }
                    
                    break;
                
                case 2:
                    System.out.print("Voici la liste des points de votre plan :\n");
                    for (Node node : ville.getNodes()){
                        System.out.println(node.getName() + " x: " + node.getX() + " y: " + node.getY() + "\n");
                    }
                    System.out.print("Veuillez saisir le nom point de départ et d'arrivé séparés par un espace (ex : pointDepart pointArrive): ");
                    String input2 = scanner.nextLine();  // Lire toute la ligne
                    String[] parts2 = input2.split(" ");  // Diviser la ligne en parties
            
                    // Convertir les parties en entiers
                    String pointDepart = parts2[0];
                    String pointArrive = parts2[1];
                    ville.addLiaison(pointDepart, pointArrive);
                    break;
                
                case 3:
                    System.out.print("Voici la liste des points de votre plan :\n");
                    for (Node node : ville.getNodes()){
                        System.out.println(node.getName() + " x: " + node.getX() + " y: " + node.getY() + "\n");
                    }
                    System.out.print("Veuillez saisir le nom point de départ et d'arrivé séparés par un espace (ex : pointDepart pointArrive): ");
                    String input3 = scanner.nextLine();  // Lire toute la ligne
                    String[] parts3 = input3.split(" ");  // Diviser la ligne en parties
            
                    // Convertir les parties en entiers
                    String pointDepartdijkstra = parts3[0];
                    String pointArrivedijkstra = parts3[1];
                    dijkstraAlgo(ville, ville.getNodeByName(pointDepartdijkstra), ville.getNodeByName(pointArrivedijkstra), display);
                    clearConsole();
                    System.out.print("Voici le chemin optimal entre " + pointDepartdijkstra + " et " + pointArrivedijkstra + " : \n");
                    display.printScreen();
                    System.out.print("Entrez n'importe quoi pour passer a la suite : ");
                    scanner.nextLine();
                    break;
                
                default:
                    break;
            }

            // Fermer le scanner (facultatif mais recommandé)
            //scanner.close();
        }
        //clearConsole();
        //dijkstraAlgo(ville, nodeA, nodeC, display);
        //display.printScreen();
    }

    public static void printGraph(Graph graph, Display display, char car) {
        for (int i=0; i<graph.getNodes().size(); ++i){
            for (int j=0; j<graph.getNodes().get(i).getConnectNode().size(); ++j){
                display.addLine(graph.getNodes().get(i).getX(), graph.getNodes().get(i).getY(), graph.getNodes().get(i).getConnectNode().get(j).getX(), graph.getNodes().get(i).getConnectNode().get(j).getY(), car);
            }
        }
        for (int i=0; i<graph.getNodes().size(); ++i){
            display.addPoint(graph.getNodes().get(i).getX(), graph.getNodes().get(i).getY(), '#');
        }
    }

    public static void dijkstraAlgo(Graph graph, Node node1, Node node2, Display display) {
        Dijkstra dijkstra = new Dijkstra();
        List<Node> path = dijkstra.dijkstra(graph, node1, node2);

        System.out.println("Chemin de rue1 à rue3:");
        Graph villedijkstra = new Graph("toulousedijkstra", 50, 50);
        for (Node node : path) {
            System.out.println(node.getName() + " (" + node.getX() + ", " + node.getY() + ")");
            villedijkstra.addNode(new Node(node.getX(), node.getY(), node.getName()));
        }
        for (int i=0; i<path.size()-1; ++i) {
            villedijkstra.addLiaison(path.get(i).getName(), path.get(i+1).getName());
        }
        printGraph(villedijkstra, display, '█');
    }

    public static void clearConsole() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isNumeric(String str) {
        return str.matches("\\d+");
    }
    
}
