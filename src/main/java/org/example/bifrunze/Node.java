package org.example.bifrunze;

public class Node {
    private int value;
    private Node left;
    private Node right;

    public Node(int value, Node left, Node right){
        this.value = value;
        this.right= right;
        this.left= left;
    }

    public Node(){}

    public int getValue(){
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getLeft(){
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight(){
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}
