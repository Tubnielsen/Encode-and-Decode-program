/*
      Projekt del 3, Algoritmer og Datastrukturer, 507
              betou17, Benjamin Toubøl, H9
              mipos17, Mikkel Boger Posselt, H9
              tobia17, Tobias B Nielsen, H9
*/
class DictBinTree implements Dict{
    public Node root; //Vi sætter at alle instancer af Classen har node sat som root
    private int size = 0;
    private int counter = 0;
    public DictBinTree(){
        root = null;
    } //og at den er null, givet intet er sat ind.

    public void treeInsert(Node n, Node z){//Function som sætter en Tree struktur for Node Objects, den tager 2 argumenter
        //som begge er Node Objects. Hvori at Node z, er den Node som skal sættes ind i tree strukuren og Node n er root. Som kan ses i public insert, som kalder på denne private
        Node y = null;
        Node x = n;
        while (x != null){ //hvis værdien som indsættes ikke er null, så skal man finde ud af hvor langt nede i tree strukturen node z skal være. while loopet checker om z er,
            // mindre eller større end root og sætter den som værende leftchild hvis den er mindre og righthvis den er større. i starten af hvert loop gemmes den forrige værdi af x til at være y
            // Endtil at den finder en værdi som er null, hvor til at programmet springer ud af loopet og z vil sættes til at være child af y, givet det var den sidste værdi inden
            // en null værdi blev fundet, hvorefter at vi sætter vores node z til at være child af y.

            y = x;
            if (z.getKey() < x.getKey()){
                x = x.getLeftChild();
            } else  if (z.getKey() > x.getKey())  {
                x = x.getRightChild();
            }
            else{
                break;
            }
        }
        z.setParent(y);
        if (y == null ) { //hvis at y er null, vil det sige at tree er tomt, og den sættes til at være root
            root = z;
        }else if(z.getKey() == y.getKey()) {
            size--;
        } else if (z.getKey() < y.getKey()&& z.getKey() != y.getKey()){ //ellers skal vi finde ud af om den skal sættes til at være left eller right child, altever om int værdien er større eller mindre end dens parent.
            y.setLeftChild(z);
        } else if (z.getKey() > y.getKey()&& z.getKey() != y.getKey()){
            y.setRightChild(z);
        }
        size++; //vi inkrementere size for at holde styr på tree strukturens størrelse. og skal bruges når at vi vil lave en liste med vores værdier.
    }

    private boolean treeSearch(Node x, int k){ //treesearch tager 2 argumenter Node x, som er vores root, og int k som er den node vi skal tjekke om allerede er træet.
        if (x == null){//hvis root er null er der åbenlyst ikke noget i træet.
            return false;
        }
        else if (k == x.getKey()){//hvis at k er vores root node, så værdien fundet og vi behøver ikke søge mere og returner true.
            return true;
        }
        else if (k < x.getKey()){ //ellers skal vi tjekke om k er større eller mindre end root, og kalder rekursivt funktionen hvor at left eller rightchild sættes som root altefter om værdien var større eller mindre end root.
            return treeSearch(x.getLeftChild(), k);
        }else { return treeSearch(x.getRightChild(), k); }
    }

    private void inorderTreeWalk(String[] strings, String s, Node x){ // inorder tager 2 argumenter, en liste af strings, en String s, og node x, hvor at x er root
        if (x != null){
            inorderTreeWalk(strings, s+"0", x.getLeftChild()); //Her forlænges s med 0, da der bliver bevæget til venstre i træet.

            if (x.bitValue != -1){strings[x.bitValue] = s;}

            inorderTreeWalk(strings, s+"1", x.getRightChild()); //Her forlænges s med 1, da der bliver bevæget til højre i træet.
        }

    }


    public void insert(int k){ //public tager kun den int som skal indsættes og en ny node med den int bliver konstrueret, alle relations til andre nodes er sat null og bliver først sat i privat insert.
        Node z = new Node(k,0, null, null, null);
        treeInsert(root, z); //private insert til node root og den nye root z.
    }

   public void insertNode(Node node){ //public tager kun den int som skal indsættes og en ny node med den int bliver konstrueret, alle relations til andre nodes er sat null og bliver først sat i privat insert.

        treeInsert(root, node); //private insert til node root og den nye root z.
    }

    public boolean search(int k){
        return treeSearch(root, k);
    } //public search der kalder private med root node og den int som vi vil tjekke om en node indeholder.

    public String[] orderedTraversal(){ //public laver en liste som har samme størelse som vores size, og kalder på private og returner String listen codes.
        String[] codes = new String[256];
        inorderTreeWalk(codes, "", root);
        return codes;
    }







}
