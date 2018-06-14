/*
      Projekt del 3, Algoritmer og Datastrukturer, 507
              betou17, Benjamin Toubøl, H9
              mipos17, Mikkel Boger Posselt, H9
              tobia17, Tobias B Nielsen, H9
*/
public class Element {

    public int key;
    public int bitNumber;
    public DictBinTree data;

    public Element(int i, DictBinTree o,int b){
	     this.key = i;
	     this.data = o;
       this.bitNumber = b;
    }
}
