/*
      Projekt del 3, Algoritmer og Datastrukturer, 507
              betou17, Benjamin Toubøl, H9
              mipos17, Mikkel Boger Posselt, H9
              tobia17, Tobias B Nielsen, H9
*/
import java.util.ArrayList;
import java.util.Collections;

public class PQHeap implements PQ {
    public int maxElms; // This is the max value for the lenght of the list
    public ArrayList<Element> A = new ArrayList<>(); // This is our arraylist which holds all of the elements.

    public PQHeap(int maxElms) {
        this.maxElms = maxElms;
    } // This is a constructor for the class PQHeap, which takes a the int that will be the max value.

    public void insert(Element e) {
        if (A.size() >= maxElms) {
            System.out.println("No more item(s) can be inserted here!");
            // This statement is printed in the case that we are trying to insert an item into a full list.
        } else {
            A.add(e); // Here we add the element e to the list
            int i = A.size() - 1; // And now we set i to be the index of the element we just inserted.
            while (i > 0 && (A.get(Parent(i))).key > (A.get(i)).key){
                Collections.swap(A, Parent(i), i); // Here we swap the values at the index of the element we just inserted, and it's parent.
                //This only happens, if the value of the paren is bigger than the value of the child.
                i = Parent(i); // Now we change the value of i to be equal to the index we just moved the element we inserted to.
            }
        }
    }

    public Element extractMin() {
        if (A.size() == 1) {
            Element min = A.get(0);
            A.remove(A.get(0));
            return min;
        } // The above is a security messure. This is to make life easier for the program, so that in the case, there is only one element in the list, we just get that element.
        else {
            Element min = A.get(0);
            Collections.swap(A, 0, A.size() - 1); // Here we swap the values of the final element and the first element.
            A.remove(A.get(A.size() - 1)); //  Here we then remove the final element which is the smallest in the list.
            heapify(A, 0); // We the call heapify on the list to again sort it to be a minimum heap.
            return min;
        }
    } //

    public void heapify(ArrayList<Element> A, int i) {
        int l = Left(i); // Here we use the Left function to make l equal to the first child of i.
        int r = Right(i); // Here we use the Right function to make r equal to the second child of i.
        int min; // Here we initialize the int min, that later will store the smallest value druing heapify.
        if (l < A.size() && (A.get(l)).key < (A.get(i)).key) {
            min = l; // Here we give min the value of l, if the element at index l's key is smaller than the element at index i's key.
        } else {
            min = i; // Here we set min equal to i's key, if the above condition isn't true.
        }
        if (r < A.size() && (A.get(r)).key < (A.get(min)).key) {
            min = r; // Here we give min the value of r, if the element at index r's key is smaller than the element at index i's key.
        }
        if (min != i) {
            Collections.swap(A, i, min); // If min isn't i, then we swap the values at their indexes.
            heapify(A, min); //And then we make a recursive call on heapify, where we now use min as the i value.
        }
    }

    public void printList(){
        for (int i = 0; i < A.size(); i++){
        System.out.println("Key: " + (A.get(i)).key + ", Data: " + (A.get(i)).data);
      } // This is a function we made for printing all the key's and data in all elements in the list.
    }

    public int Parent(int i){
        return (i-1)/2;
      } //This is the PArent function, that returns the parent's index of an index.

    public int Left(int i){
        return 2*i+1;
      } //This is the Left function, that returns the first child's index of an index.

    public int Right(int i){
        return 2*i+2;
      } //This is the Right function, that returns the second child's index of an index.

    public static void main(String[] args) {}
}
