/*
      Projekt del 3, Algoritmer og Datastrukturer, 507
              betou17, Benjamin Toubøl, H9
              mipos17, Mikkel Boger Posselt, H9
              tobia17, Tobias B Nielsen, H9
*/
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Encode {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        int[] thisMany = null; // Initialiserer listen til holdningen af hyppigheder.
        thisMany = fileReader(new File(args[0])); // Kører fileReader funktion og gemmer resultatet i thisMany.
      	DictBinTree dbt = huffman(thisMany).data; // Gemmer træet lavet fra huffman algoritmen.
        String[] keys = dbt.orderedTraversal(); // Laver en ny liste, hvor den kører vores public orderedTraversal, hvor den sortere så den overholder et træstruktur.

        InputStream is = new FileInputStream(new File(args[0]));
        OutputStream os = new FileOutputStream(new File(args[1]));
        BitOutputStream bos = new BitOutputStream(os);
        int num;
        for (int i: thisMany){
          bos.writeInt(i); // I dette loop skriver vi alle hyppighederne fra thisMany.
        }

        while ((num = is.read()) != -1){
          String readNum = keys[num]; // Her finder vi koden for read integeren.
          for (String s: readNum.split("")){
            bos.writeBit(Integer.parseInt(s)); // I dette indlejrede loop, skriver vi koden til outputstream.
          }
        }


    }
	// Filereader() læser en fil og laver en liste med størrelse 256, som tæller hyppigheden af de forskellige bit-strenge,
	// Listens indekser repræsenterer bit-strengene, f.eks. occurences[5] repræsenterer bitstrengen 00000101.
    public static int[] fileReader(File file) throws FileNotFoundException{
        int[] occurences = new int[256];
        InputStream is = new FileInputStream(file);
        int num;
        try {
            while ((num = is.read()) != -1){
                occurences[num] += 1;

            }
        } catch (IOException ex) {}
        return occurences;
    }

    public static Element huffman(int[] readList){
        PQHeap q = new PQHeap(readList.length); // Laver et PQHeap træ med længden sat til input listens længde.
        for (int i = 0; i < readList.length; i++){ // I for-loopet køre vi igennem input listen readList[] og indsætter hvert indeks ind i et PQHeap.
            DictBinTree newTree = new DictBinTree();
            newTree.insert(readList[i]);
            q.insert(new Element(readList[i], newTree,i));
        }

        int g = 0;
        while(q.A.size()!=1){ //I dette while loop laver vi et nyt træ og en ny node, hvorefter vi extracter to nye elementer.
            DictBinTree dbt = new DictBinTree();
            Node z = new Node(0,-1,null,null,null);
            Element x = q.extractMin();
            Element y = q.extractMin();
            int frequency = (x.key+y.key);
            if( frequency !=0){ //Hvis frequency ikke er lig med 0 (og derved er større end nul) så sætter vi node z's left og right childs til de to extractede elementers data.
                z.setKey(frequency);
                z.setLeftChild(x.data.root);
                z.getLeftChild().bitValue=x.bitNumber; //Derudover sætter vi deres bitValue til at være elementet's bitNumber.
                z.setRightChild(y.data.root);
                z.getRightChild().bitValue=y.bitNumber;
                g++;
            }
            dbt.root = z;

            q.insert(new Element(frequency,dbt,-1));
        }
        return q.extractMin();
    }
}
