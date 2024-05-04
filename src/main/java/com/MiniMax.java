package com;


public class MiniMax {
    Node root;

    public MiniMax(Board board) {
        root = new Node();
    }

    public Node getRoot () {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    @Override
    public String toString() {
        return root.toString();
    }
    
    
    
}
