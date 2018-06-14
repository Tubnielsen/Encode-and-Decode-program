/*
      Projekt del 3, Algoritmer og Datastrukturer, 507
              betou17, Benjamin Toubøl, H9
              mipos17, Mikkel Boger Posselt, H9
              tobia17, Tobias B Nielsen, H9
*/
public class Node{ //Class Node er et object vi med angivet værdier, som holder en Int, og information om andre Node Objects
    private Node rightChild = null;
    private Node leftChild = null;
    private Node parent = null;
    public int key;
    public int bitValue;

    public Node(int key, int bitValue ,Node rightChild, Node leftChild, Node parent){ //Constructor til Object Node,
        this.key = key;
        this.bitValue = bitValue;
        this.rightChild = rightChild;
        this.leftChild = leftChild;
        this.parent = parent;
    }
    public Node getRightChild(){ return this.rightChild; } //Getters og setters til Node Objects
    public void setRightChild(Node n){
        this.rightChild = n;
    }
    public Node getLeftChild(){ return this.leftChild; }
    public void setLeftChild(Node n){
        this.leftChild = n;
    }
    public int getKey() { return this.key; }
    public void setKey(int i){
        this.key = i;
    }

    public int getBitValue() { return this.bitValue; }
    public void setBitValue(int i){
        this.bitValue = i;
    }
    public Node getParent(){ return this.parent; }
    public void setParent(Node n){
        this.parent = n;
    }

    public static void main(String args[]){

    }
}
