package com;

public class Tree {
    Node root;
    
    Tree(){
        root = null;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    @Override
    public String toString() {
        return root.moves.toString();
    }
}