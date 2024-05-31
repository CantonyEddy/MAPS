package com.maps.api.models;

import java.util.ArrayList;
import java.util.List;

public class Node {

    private int x;
    private int y;
    private String name;
    private List<Node> connectNode;

    public Node(int x, int y, String name){
        this.x = x;
        this.y = y;
        this.name = name;
        this.connectNode = new ArrayList<>();
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public String getName(){
        return name;
    }

    public List<Node> getConnectNode(){
        return connectNode;
    }

    public void addNode(Node node){
        this.connectNode.add(node);
    }

    public void removeNode(Node node){
        this.connectNode.remove(node);
    }


}
