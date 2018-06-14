/*
      Projekt del 3, Algoritmer og Datastrukturer, 507
              betou17, Benjamin Toubøl, H9
              mipos17, Mikkel Boger Posselt, H9
              tobia17, Tobias B Nielsen, H9
*/
public interface Dict {
    public void insert(int k);
    public void insertNode(Node node);
    public String[] orderedTraversal();
    public boolean search(int k);
}
