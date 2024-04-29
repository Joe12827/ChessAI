package com;


public class MiniMax {
    Tree tree;

    public MiniMax(Board board) {
        tree = new Tree();
        Node root = new Node();
        tree.setRoot(root);
    }

    public Tree getTree () {
        return tree;
    }
    
    
    
}
